package com.keremdemir.flightradar.ui.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun RegisterScreen(onButtonClicked :() -> Unit){
    Column {
        Spacer(modifier = Modifier.height(44.dp)

        )
        Text(text = "Register Screen", modifier = Modifier.align(Alignment.CenterHorizontally),)
        Spacer(modifier = Modifier.height(24.dp)

        )
        Button(onClick = {
            onButtonClicked()
        }) {
            Text(text = "Open Login Screen")
        }
    }

}