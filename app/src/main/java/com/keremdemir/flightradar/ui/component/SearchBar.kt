package com.keremdemir.flightradar.ui.component
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun FRSearchBar(modifier: Modifier = Modifier,
              hint: String = "Search flight number",
              onSearch: (String) -> Unit = {}
) {
    var searchInput by remember {
        mutableStateOf("")
    }

    var isHintDisplayed by remember {
        mutableStateOf(hint != "")
    }

    Row{
        Box(
            modifier = modifier
                .border(width = 1.dp,Color.Gray, shape = RoundedCornerShape(10.dp))
                .clip(shape = RoundedCornerShape(10.dp))
                .background(color = Color.White)
                .padding(20.dp, 10.dp, 30.dp, 10.dp), contentAlignment = Alignment.CenterStart
        ) {

            BasicTextField(
                value = searchInput,
                onValueChange = {
                    searchInput = it
                    onSearch(it)
                },
                maxLines = 1,
                singleLine = true,
                textStyle = TextStyle(
                    fontSize = 22.sp,
                    fontWeight = FontWeight.W400
                ),
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .fillMaxWidth()
                    .onFocusChanged {
                        isHintDisplayed = !it.isFocused
                    }
            )
            IconButton(
                modifier=
                Modifier.align(Alignment.BottomEnd)
                    .size(35.dp),
                onClick = {}) {
                Icon(
                    Icons.AutoMirrored.Filled.ArrowForward,
                    contentDescription = "Arrow Forward",
                    modifier=Modifier.fillMaxSize()
                )
            }


            if (isHintDisplayed) {
                Text(
                    text = hint,
                    fontSize = 22.sp, // Set the font size of the hint text
                    modifier = Modifier.align(Alignment.CenterStart) // Align the hint text to the center start
                )
            }
        }
    }
}