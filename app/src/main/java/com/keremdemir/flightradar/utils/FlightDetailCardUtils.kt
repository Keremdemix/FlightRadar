package com.keremdemir.flightradar.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.Duration
import java.time.OffsetDateTime

object Utils {

    @RequiresApi(Build.VERSION_CODES.O)
    fun formatLocalTime(dateTime: OffsetDateTime): String {
        return dateTime.toLocalTime().toString()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun formatLocalDate(dateTime: OffsetDateTime): String {
        return dateTime.toLocalDate().toString()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun formatDuration(duration: Duration): String {
        val hours = duration.toHours()
        val minutes = duration.toMinutes() - (60 * hours)
        return "${hours}h ${minutes}m"
    }
}
