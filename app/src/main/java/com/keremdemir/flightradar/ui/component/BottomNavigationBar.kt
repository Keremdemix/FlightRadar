
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.keremdemir.flightradar.R


@Composable
fun FRBottomNavigationBar(navController: NavController,) {
        BottomAppBar(
            windowInsets = WindowInsets(0,0, 0,0),
            modifier = Modifier
                .padding(bottom = 0.dp)
                .height(130.dp),
            containerColor =Color.Transparent
            ,/* colorResource(R.color.light_blue)*/
            contentPadding = PaddingValues(0.dp),
        )
        {
            Box(modifier = Modifier.background(color = Color.Transparent)) {
                Column(modifier = Modifier.background(color = Color.Transparent)) {
                    Row(
                        modifier = Modifier
                            .background(color = Color.Transparent)
                            .height(30.dp)
                            .fillMaxWidth()
                    ) {


                    }
                    Row(
                        modifier = Modifier
                            .background(color = colorResource(R.color.light_blue))
                            .height(100.dp)
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        IconButton(
                            modifier = Modifier
                                .padding(horizontal = 60.dp)
                                .size(35.dp),
                            onClick = {
                                navController.navigate(BottomNavItem.Flights.route)
                            }) {
                            Image(
                                painter = painterResource(R.drawable.fly),
                                contentDescription = "Flights",
                                modifier = Modifier
                                    .fillMaxSize()
                            )
                        }
                        IconButton(
                            modifier = Modifier
                                .padding(horizontal = 60.dp)
                                .size(35.dp),
                            onClick = {
                                navController.navigate(BottomNavItem.Destinations.route)
                            }) {
                            Icon(
                                Icons.Filled.LocationOn,
                                contentDescription = "Destinations",
                                modifier = Modifier.fillMaxSize()
                            )
                        }

                    }

                }
                Box(
                    modifier = Modifier
                        .size(100.dp) // Ensure the Box is a square to retain the circular shape
                        .clip(shape = CircleShape) // Clip the Box itself to be circular
                        .background(Color.White)
                        .align(Alignment.Center)// Apply background after clipping
                ) {
                    IconButton(
                        onClick = {
                            navController.navigate(BottomNavItem.Home.route)
                        },
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


sealed class BottomNavItem(val route: String, val icon: ImageVector, val label: String) {
    object Home : BottomNavItem("home", Icons.Default.Home, "Home")
    object Flights : BottomNavItem("flights", Icons.Default.Face, "Flights")
    object Destinations : BottomNavItem("destinations", Icons.Default.LocationOn, "Destinations")
}

