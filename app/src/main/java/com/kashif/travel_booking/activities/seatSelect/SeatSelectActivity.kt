package com.kashif.travel_booking.activities.seatSelect

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.kashif.travel_booking.R
import com.kashif.travel_booking.activities.domain.FlightModel
import com.kashif.travel_booking.activities.splash.StatusTopBarColor
import com.kashif.travel_booking.activities.ticketDetail.TicketDetailActivity

class SeatSelectActivity : AppCompatActivity() {
    private lateinit var flight:FlightModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        flight=intent.getSerializableExtra("flight") as FlightModel

        setContent{
            StatusTopBarColor()
            SeatListScreen(flight = flight, onBackClick = {finish()}, onConfirm = {
                val intent=Intent(this,TicketDetailActivity::class.java).apply {
                    putExtra("flight",flight)
                }
                startActivity(intent,null)
            })
        }
    }
}