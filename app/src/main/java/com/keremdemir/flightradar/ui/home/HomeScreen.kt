package com.keremdemir.flightradar.ui.home


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.keremdemir.flightradar.R
import com.keremdemir.flightradar.ui.component.FRSearchBar
import com.keremdemir.flightradar.ui.viewmodel.DestinationViewModel
import com.keremdemir.flightradar.ui.viewmodel.FlightsViewModel

@Composable
fun HomeScreen(/*onHomeButtonClick:()->Unit,onFlightButtonClick:()->Unit,onDestinationButtonClick:()->Unit*/) {
    val flightsViewModel: FlightsViewModel = viewModel()
    val destinationViewModel:DestinationViewModel= viewModel()

    val destination=destinationViewModel.destinations.observeAsState().value?.destinations
    val flights = flightsViewModel.flights.observeAsState().value?.flights

    Scaffold (
        bottomBar = {
            MyBottomAppBar()
        }
    ) { _ ->
            Box {
                Column(
                    modifier = Modifier
                        .padding(bottom = 120.dp)
                        .fillMaxSize()
                        .background(Color.White)
                ) {

                    Top()

                    Column (modifier = Modifier.verticalScroll(rememberScrollState())){
                        Row(Modifier.padding(top = 60.dp, start = 24.dp)) {
                            Text(
                                text = "Top Flights",
                                fontWeight = FontWeight.Bold,
                                fontSize = 26.sp,

                            )
                            Image(
                                painter = painterResource(id = R.drawable.fly),
                                contentDescription = "",
                                modifier = Modifier
                                    .padding()
                                    .size(27.dp)
                                    .align(Alignment.CenterVertically)
                            )
                        }

                        if (flights != null) {
                            Column(

                            ) {
                                flights.take(3).forEach { flight ->
                                    FlightCard(
                                        modifier = Modifier.padding(0.dp),
                                        flightName = flight.flightName,
                                        prefixICAO = flight.prefixICAO,
                                        destination = flight.route.destinations[0]
                                    )
                                }
                            }
                        } else {
                            HomeEmptyView()
                        }

                        Row (modifier = Modifier
                            .padding(horizontal = 24.dp)
                            .fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween){
                            TextButton(onClick = {}) {
                                Text(text = "See More Flights...")
                            }
                            TextButton(onClick = {}) {
                                Text(text = "See Favourite Flights...")

                            }
                        }

                        Text(
                            text = "Destinations",
                            fontWeight = FontWeight.Bold,
                            fontSize = 26.sp,
                            modifier = Modifier.padding(start = 24.dp)
                        )

                        if(destination!=null){
                            LazyRow(modifier = Modifier.padding(start =  20.dp)) {
                                items(destination){destination->
                                    DestinationCard()
                                }
                            }
                        }



                    }

                    /*
                    if (flights != null){
                        LazyColumn {
                            items(flights){ flight ->
                                FlightCard(
                                    modifier = Modifier.padding(0.dp),
                                    flightName = flight.flightName,
                                    prefixICAO = flight.prefixICAO,
                                    destination = flight.route.destinations[0])
                            }
                        }
                    } else {
                        HomeEmptyView()
                    }
                    */

                }

                FRSearchBar(
                    modifier = Modifier
                        .padding(top = 212.dp)
                        .padding(horizontal = 24.dp)
                )
            }
    }
}


@Composable
fun MyBottomAppBar(){
    BottomAppBar(
        tonalElevation = 0.dp,
        modifier = Modifier
            .padding(bottom = 0.dp),
        containerColor = colorResource(R.color.light_blue),
        contentPadding = PaddingValues(0.dp),
    )
    {
        Box {
            Column {
                Row (modifier = Modifier
                    .background(color = Color.White)
                    .height(30.dp)
                    .padding(0.dp)
                    .fillMaxWidth()){


                }
                Row(modifier = Modifier
                    .background(color = colorResource(R.color.light_blue))
                    .height(90.dp)
                    .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    IconButton(
                        modifier= Modifier
                            .padding(horizontal = 60.dp)
                            .size(35.dp),
                        onClick = {}) {
                        Image(painter = painterResource(R.drawable.fly),
                            contentDescription = "Arrow Forward",
                            modifier=Modifier
                                .fillMaxSize()
                        )
                    }
                    IconButton(
                        modifier= Modifier
                            .padding(horizontal = 60.dp)
                            .size(35.dp),
                        onClick = {}) {
                        Icon(
                            Icons.Filled.LocationOn,
                            contentDescription = "Arrow Forward",
                            modifier=Modifier.fillMaxSize()
                        )
                    }

                }

            }
            Box(
                modifier = Modifier
                    .size(80.dp) // Ensure the Box is a square to retain the circular shape
                    .clip(shape = CircleShape) // Clip the Box itself to be circular
                    .background(Color.White)
                    .align(Alignment.Center)// Apply background after clipping
            ) {
                IconButton(
                    onClick = {},
                    modifier = Modifier
                        .align(Alignment.Center)
                        .size(80.dp)
                ) {
                    Icon(
                        Icons.Outlined.Home,
                        contentDescription = "Home",
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        }


    }
}



@Composable
fun HomeEmptyView(){
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "No data")
    }
}


@Composable
fun Top (){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize()
            .background(
                colorResource(R.color.light_blue)
            )) {
        Text(
            modifier= Modifier
                .padding(40.dp, 120.dp, 80.dp, 40.dp)
                .fillMaxWidth(),
//            fontStyle = FontStyle.Italic,
            fontWeight= FontWeight.W700,
            color = Color.White,
            fontSize = 34.sp,
            text = stringResource(id = R.string.home_header)
        )
    }
}


@Composable
fun FlightCard(modifier: Modifier,flightName:String,prefixICAO:String,destination:String){
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 5.dp),
            shape = CardDefaults.shape,
            colors = CardDefaults.cardColors(Color.White)
        ) {
            Column(modifier= Modifier

                .clip(shape = RoundedCornerShape(15.dp))
                .border(1.dp, color = Color.Gray, shape = RoundedCornerShape(15.dp))
            ){
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
                    verticalAlignment = Alignment.CenterVertically,
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
                                text = flightName
                            )
                        }}
                    Image(
                        modifier = Modifier
                            .size(32.dp)
                            .padding(end = 16.dp),
                        alignment = Alignment.Center,
                        painter = painterResource(R.drawable.fav_icon),
                        contentDescription ="favourite icon"
                    )
                }
                HorizontalDivider()
                Row(modifier = Modifier
                    .padding(5.dp)
                    .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,) {
                    Column {
                        CardDateText(modifier = Modifier,prefixICAO)
                        CardDateText(modifier = Modifier,"8.00")
                        CardDateText(modifier = Modifier,"T7 05/02/2024")
                    }
                    Column(horizontalAlignment = Alignment.CenterHorizontally){
                        Text(text = stringResource(id = R.string.flight_duration))
                        Image(painter = painterResource(id = R.drawable.arrow), contentDescription ="arrow" )

                    }
                    Column {
                        CardDateText(modifier = Modifier,destination)
                        CardDateText(modifier = Modifier,"9.00")
                        CardDateText(modifier = Modifier,"T7 05/02/2024")

                    }
                }

            }
        }

}


@Composable
fun CardDateText(modifier: Modifier,myString: String){
    Text(
        fontSize =18.sp ,
        fontWeight = FontWeight.W500,
        text =myString
    )
}


@Composable
fun DestinationCard(){
    Card (
        modifier = Modifier
            .padding(top = 10.dp, end = 10.dp, start = 4.dp, bottom = 10.dp)
            .shadow(4.dp),
        colors =CardDefaults.cardColors(containerColor = Color.White)){
        Column() {
            Image(modifier = Modifier
                .padding(horizontal = 10.dp)
                .fillMaxWidth(),
                painter = painterResource(R.drawable.scene),
                contentDescription ="Scene",
                contentScale = ContentScale.Crop

            )

            Row (modifier = Modifier
                .padding(horizontal = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween){
                Text(text = "Istanbul")
                Text(text = "SAW")
            }
            Text(
                text = "Turkey",
                modifier=Modifier.padding(horizontal = 10.dp)
            )
        }
    }

}



@Preview
@Composable
fun PreviewKeremDemir() {
    DestinationCard()
}
