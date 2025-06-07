package com.kashif.travel_booking.activities.domain

import java.io.Serializable

/**
 * Created by Mohammad Kashif Ansari on 06,June,2025
 */
data class FlightModel(
    var AirlineLogo:String="",
    var Airlinename:String="",
    var ArrivalTime:String="",
    var ClassSeat:String="",
    var Date:String="",
    var From:String="",
    var FromShort:String="",
    var NumberSeat:Int=0,
    var Price:Double=0.0,
    var Passenger:String="",
    var Seat:String="",
    var ReservedSeat:String="",
    var Time:String="",
    var To:String="",
    var ToShort:String=""
):Serializable
