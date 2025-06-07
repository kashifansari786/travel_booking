package com.kashif.travel_booking.activities.dashboard

import android.widget.Toast
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomAppBar
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kashif.travel_booking.R

/**
 * Created by Mohammad Kashif Ansari on 03,June,2025
 */
@Composable
fun prepareBottomMenu():List<BottomMenuItem>{

    return listOf(
        BottomMenuItem(label = "Home", icon = painterResource(R.drawable.bottom_btn1)),
        BottomMenuItem(label = "Home", icon = painterResource(R.drawable.bottom_btn2)),
        BottomMenuItem(label = "Home", icon = painterResource(R.drawable.bottom_btn3)),
        BottomMenuItem(label = "Home", icon = painterResource(R.drawable.bottom_btn4)),
    )
}

@Composable
@Preview
fun MyBottomBar(){
 val bottomMenuItemsList= prepareBottomMenu()
 val context= LocalContext.current
 var selectedItem by remember { mutableStateOf("Home") }

    BottomAppBar(backgroundColor = colorResource(R.color.darkPurple), elevation = 3.dp) {
        bottomMenuItemsList.forEach{bottomMenuItem->
            BottomNavigationItem(
                selected = (selectedItem==bottomMenuItem.label),
                onClick = {
                    selectedItem=bottomMenuItem.label
                    if(bottomMenuItem.label=="Cart"){

                    }else{
                        Toast.makeText(context,bottomMenuItem.label,Toast.LENGTH_SHORT).show()
                    }
                }, icon = {
                    Icon(painter = bottomMenuItem.icon,
                        contentDescription = null,
                        tint = colorResource(R.color.orange),
                        modifier = Modifier
                            .padding(top = 8.dp)
                            .size(20.dp))
                }
            )
        }
    }
}