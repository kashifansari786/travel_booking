package com.kashif.travel_booking.viewModel

import androidx.lifecycle.LiveData
import com.kashif.travel_booking.activities.domain.FlightModel
import com.kashif.travel_booking.activities.domain.LocationModel
import com.kashif.travel_booking.repository.MainRepository

/**
 * Created by Mohammad Kashif Ansari on 04,June,2025
 */
class MainViewModel {

    private val repository = MainRepository()

    fun loadLocations():LiveData<MutableList<LocationModel>>{
        return repository.loadLocation()
    }

    fun loadFilterd(from:String,to:String):LiveData<MutableList<FlightModel>>{
        return repository.loadFiltered(from,to)
    }

}