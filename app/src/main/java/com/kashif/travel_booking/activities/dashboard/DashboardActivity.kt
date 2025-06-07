package com.kashif.travel_booking.activities.dashboard

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.kashif.travel_booking.R
import com.kashif.travel_booking.activities.domain.LocationModel
import com.kashif.travel_booking.activities.searchResult.SearchResultActivity
import com.kashif.travel_booking.activities.splash.GradientButton
import com.kashif.travel_booking.activities.splash.StatusTopBarColor
import com.kashif.travel_booking.viewModel.MainViewModel

class DashboardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent{
            MainScreen()
        }
    }
}

@Composable
@Preview
fun MainScreen(){

    val locations= remember { mutableStateListOf<LocationModel>() }
    val viewModel=MainViewModel()
    var showLocationLoading by remember { mutableStateOf(true) }
    var from:String=""
    var to:String=""
    var classes:String=""
    var adultPassenger:String=""
    var childPassenger:String=""
    val context= LocalContext.current
    StatusTopBarColor()
    LaunchedEffect(Unit) {
        viewModel.loadLocations().observeForever{result->
            locations.clear()
            locations.addAll(result)
            showLocationLoading=false

        }
    }
    val scafoldState= rememberScaffoldState()
    StatusTopBarColor()
    Scaffold(bottomBar = { MyBottomBar() },
        scaffoldState = scafoldState
    ) {
        paddingValues ->
        LazyColumn (modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(R.color.darkPurple2))
            .padding(paddingValues = paddingValues)){
            item { TopBar() }
            item {
                Column(modifier = Modifier
                    .padding(32.dp)
                    .background(colorResource(R.color.darkPurple),
                        shape = RoundedCornerShape(20.dp))
                    .fillMaxWidth()
                    .padding(vertical = 16.dp, horizontal = 24.dp))
                {
                    //from section
                    YelowTitle("From")

                    val locationNames:List<String> = locations.map{it.Name}
                    DropDownMenuList(items = locationNames, loadingIcon = painterResource(R.drawable.from_ic),
                        hint = "Select Origin",
                        showLocationLoading = showLocationLoading) {selectedItem->
                        from=selectedItem
                            
                    }
                    Spacer(modifier = Modifier.height(16.dp))

                    //to section
                    YelowTitle("To")
                    DropDownMenuList(items = locationNames, loadingIcon = painterResource(R.drawable.from_ic),
                        hint = "Select Destination",
                        showLocationLoading = showLocationLoading) {selectedItem->
                        to=selectedItem

                    }
                    Spacer(modifier = Modifier.height(16.dp))

                    //passenger counter
                    YelowTitle("Passengers")
                    Row (modifier = Modifier.fillMaxWidth()){
                        PassengerCounter(title = "Adult",
                            modifier = Modifier.weight(1f),
                            onItemSelected ={adultPassenger=it} )
                        Spacer(modifier = Modifier.width(16.dp))
                        PassengerCounter(title = "Child",
                            modifier = Modifier.weight(1f ),
                            onItemSelected ={childPassenger=it} )
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    //valender picker
                    Row {
                        YelowTitle("Departure Date",Modifier.weight(1f))
                        Spacer(modifier = Modifier.width(16.dp))
                        YelowTitle("Return Date",Modifier.weight(1f))
                    }
                    DatePickerScreen(modifier = Modifier.weight(1f))
                    Spacer(modifier = Modifier.height(16.dp))
                    //classes section
                    YelowTitle("Class")
                    val classItems= listOf("Business class","First class","Economy class")
                    DropDownMenuList(items = classItems, loadingIcon = painterResource(R.drawable.from_ic),
                        hint = "Select Class",
                        showLocationLoading = showLocationLoading) {selectedItem->
                        to=selectedItem

                    }
                    //search button
                    Spacer(modifier = Modifier.height(16.dp))
                    GradientButton(
                        onCLick = {
                            val intent=Intent(context,SearchResultActivity::class.java).apply {
                                putExtra("from",from)
                                putExtra("to",to)
                                putExtra("numPassenger",adultPassenger+childPassenger )
                            }
                            startActivity(context,intent,null)
                        }, text = "Search"
                    )
                }
            }
        }
    }
}

@Composable
fun YelowTitle(text:String,modifier: Modifier=Modifier){
    Text(text=text, fontWeight = FontWeight.SemiBold, color = colorResource(R.color.orange),modifier=modifier)
}