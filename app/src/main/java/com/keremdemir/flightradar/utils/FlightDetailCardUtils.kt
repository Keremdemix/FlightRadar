package com.keremdemir.flightradar.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.Duration
import java.time.LocalDateTime

object Utils {

    @RequiresApi(Build.VERSION_CODES.O)
    fun formatLocalTime(dateTime: LocalDateTime): String {
        return dateTime.toLocalTime().toString()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun formatLocalDate(dateTime: LocalDateTime): String {
        return dateTime.toLocalDate().toString()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun formatDuration(duration: Duration): String {
        val hours = duration.toHours()
        val minutes = duration.toMinutes() - (60 * hours)
        return "${
            if (minutes < 0 && hours < 22) {
                "${hours + 1} h"
            } else if (minutes > 0 && hours < 22) {
                "${hours + 2} h"
            } else {
                "${hours - 22} h"
            }
        } ${
            if (minutes < 0) {
                "${60 + minutes} m"
            } else {
                "$minutes m"
            }
        }"
    }
}
