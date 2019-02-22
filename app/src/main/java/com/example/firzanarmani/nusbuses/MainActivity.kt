package com.example.firzanarmani.nusbuses

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.FuelManager
import com.github.kittinunf.fuel.moshi.responseObject
import android.support.design.widget.Snackbar
import android.util.Log
import awaitObject
import awaitObjectResult
import awaitResponseResult
import awaitStringResponse
import com.github.kittinunf.fuel.moshi.moshiDeserializerOf
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.MainCoroutineDispatcher

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private var busStopList = ArrayList<BusStopObject.BusStopsResults.Busstop> ()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //TODO : Create indeterminate progress bar as per material guidelines: https://material.io/design/components/progress-indicators.html & https://developer.android.com/reference/android/widget/ProgressBar
        //"eventservice.svc/PickupPoint?route_code=A1" to get all points for a specific route
        //"eventservice.svc/ShuttleService?busstopname=AS7" to get all buses and their arrival times at a specific bus stop

        FuelManager.instance.basePath = "https://nextbus.comfortdelgro.com.sg"
        Fuel.get("/eventservice.svc/BusStops").responseObject<BusStopObject> { _, _, result ->
            val(success, error) = result
            if(error == null) {
                busStopList.clear()
                success!!.BusStopsResult.busstops.forEach {
                    item -> busStopList.add(item)
                }
                recyclerView = findViewById(R.id.listBusStops)
                recyclerView.layoutManager = LinearLayoutManager(this)
                recyclerView.adapter = BusStopsAdapter(this.busStopList)
            } else {
                // TODO: Snackbar with error message and refresh option
                Snackbar.make(findViewById(R.id.listBusStops), "Couldn't fetch information.", Snackbar.LENGTH_INDEFINITE).setAction("Retry", {}).show()
            }
        }
        recyclerView = findViewById(R.id.listBusStops)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = BusStopsAdapter(this.busStopList)
        Log.d("Test", busStopList.toString())

//        runBlocking { getJsonData() }

    }

//    private suspend fun getJsonData() {
//        FuelManager.instance.basePath = "https://nextbus.comfortdelgro.com.sg"
//        val (data, error) = Fuel.get("eventservice.svc/BusStops").responseObject(moshiDeserializerOf(BusStopObject::class.java))
//        Log.d("Test", data!!.toString())
//
//    }

}
