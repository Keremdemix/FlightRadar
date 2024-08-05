package com.keremdemir.flightradar.ui.home


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MonotonicFrameClock
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.keremdemir.flightradar.R
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment


@kotlin.OptIn(androidx.compose.material3.ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    Column(modifier = Modifier.background(Color.White)) {
        Top(modifier = Modifier)
        Column {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                shape =CardDefaults.shape,
                colors = CardDefaults.cardColors(Color.White)
            ) {
                Column {
                    Box (
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(color = colorResource(R.color.light_blue))
                        ){
                        Text(
                            modifier = Modifier
                                .padding(5.dp),
                            color = Color.White,
                            fontSize = 24.sp,
                            text = "Istanbul-Ankara"
                        )
                    }
                    Row (
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment =Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,

                    ){
                        Row {


                        Image(
                            painter = painterResource(id = R.drawable.ic_launcher_background),
                            contentDescription = "",
                            modifier = Modifier
                                .size(80.dp)
                                .padding(5.dp)
                                .clip(CircleShape)
                        )
                        Column {
                            Text(
                                modifier = Modifier
                                    .padding(5.dp),
                                fontWeight = FontWeight.Bold,
                                fontSize = 22.sp,
                                text = stringResource(id =R.string.company_name))
                            Text(
                                modifier = Modifier
                                    .padding(5.dp),
                                fontSize = 22.sp,
                                color = Color.Gray,
                                text = stringResource(id =R.string.flight_code))
                        }}
                        Image(
                            modifier = Modifier
                                .size(50.dp)
                                .padding(5.dp),
                            alignment = Alignment.Center,
                            painter = painterResource(R.drawable.fav_icon),
                            contentDescription ="favourite icon"
                        )
                    }
                    HorizontalDivider()
                    Row(modifier = Modifier
                        .padding(5.dp)
                        .fillMaxWidth(),
                        verticalAlignment =Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,) {
                        Column {
                            Text(text = stringResource(id = R.string.departure_location))
                            Text(text = stringResource(id = R.string.departure_time))
                            Text(text = stringResource(id = R.string.departure_date))
                        }
                        Column(horizontalAlignment = Alignment.CenterHorizontally){
                            Text(text = stringResource(id = R.string.flight_duration))
                            Image(painter = painterResource(id = R.drawable.arrow), contentDescription ="arrow" )

                        }
                        Column {
                            Text(text = stringResource(id = R.string.arrival_location))
                            Text(text = stringResource(id = R.string.arrival_time))
                            Text(text = stringResource(id = R.string.arrival_date))

                        }
                    }

                }
            }
        }
    }
}
@kotlin.OptIn(androidx.compose.material3.ExperimentalMaterial3Api::class)
@Composable
fun Top (modifier: Modifier){
    
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .size(250.dp)
            .background(
                colorResource(R.color.light_blue)
            )) {
        Spacer(modifier = Modifier.size(130.dp))
        Text(
            modifier=Modifier
                .padding(10.dp),
            fontWeight= FontWeight.Bold,
            color = Color.White,
            fontSize = 36.sp,
            text = stringResource(id = R.string.home_header))}
}

@Preview
@Composable
fun PreviewKeremDemir() {
    HomeScreen()
}
