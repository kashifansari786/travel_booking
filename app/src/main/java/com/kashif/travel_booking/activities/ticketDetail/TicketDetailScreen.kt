package com.kashif.travel_booking.activities.ticketDetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.kashif.travel_booking.R
import com.kashif.travel_booking.activities.domain.FlightModel
import com.kashif.travel_booking.activities.splash.GradientButton

/**
 * Created by Mohammad Kashif Ansari on 07,June,2025
 */

@Composable
fun TicketDetailScreen(flight:FlightModel,onBackClick:()->Unit,onDownloadTicketClick:()->Unit){
    Box(modifier = Modifier.fillMaxSize().background(colorResource(R.color.darkPurple2))){
        Column(modifier = Modifier.verticalScroll(rememberScrollState()).fillMaxSize().background(
            colorResource(R.color.darkPurple2)
        )) {
            ConstraintLayout(modifier = Modifier.fillMaxSize().background(colorResource(R.color.darkPurple2))) {
                val(topSection,ticketDetails)=createRefs()
                TicketHeader(onBackClick = onBackClick,Modifier.constrainAs(topSection){
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                })
                TicketDetailContent(flight = flight,Modifier.constrainAs(ticketDetails){
                    top.linkTo(parent.top, margin = 110.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                })
            }
            GradientButton(onCLick = {}, text = "Download Ticket")
        }
    }

}