package com.kashif.travel_booking.activities.ticketDetail

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.kashif.travel_booking.R
import com.kashif.travel_booking.activities.domain.FlightModel
import com.kashif.travel_booking.activities.splash.StatusTopBarColor

class TicketDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val  flight=intent.getSerializableExtra("flight") as FlightModel

        setContent{
            StatusTopBarColor()
            TicketDetailScreen(flight = flight, onBackClick = {finish()}, onDownloadTicketClick = {})
        }
    }
}