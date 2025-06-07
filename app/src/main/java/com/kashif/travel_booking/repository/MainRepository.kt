package com.kashif.travel_booking.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.Query
import com.google.firebase.database.ValueEventListener
import com.kashif.travel_booking.activities.domain.FlightModel
import com.kashif.travel_booking.activities.domain.LocationModel

/**
 * Created by Mohammad Kashif Ansari on 04,June,2025
 */
class MainRepository {

    private val firebaseDatabase=FirebaseDatabase.getInstance()

    fun loadLocation():LiveData<MutableList<LocationModel>>{
        val listData=MutableLiveData<MutableList<LocationModel>>()
        val ref=firebaseDatabase.getReference("Locations")
        ref.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val list= mutableListOf<LocationModel>()
                for(childSnapshot in snapshot.children){
                    val item=childSnapshot.getValue(LocationModel::class.java)
                    item?.let { list.add(it) }
                }
                listData.value=list
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
        return listData
    }

    fun loadFiltered(from:String,to:String):LiveData<MutableList<FlightModel>>{
        val listDate=MutableLiveData<MutableList<FlightModel>>()
        val ref=firebaseDatabase.getReference("Flights")
        val query=ref.orderByChild("from").equalTo(from)
        query.addListenerForSingleValueEvent(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val lists= mutableListOf<FlightModel>()
                for(childSnapshot in snapshot.children){
                    val list=childSnapshot.getValue(FlightModel::class.java)
                    if(list!=null && list.To == to){
                            lists.add(list)
                    }else
                        Log.d("inside_screen","list is null")

                }

                listDate.value=lists
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
        return listDate
    }
}