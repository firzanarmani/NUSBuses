package com.example.firzanarmani.nusbuses

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.TextView
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.FuelManager
import com.github.kittinunf.fuel.moshi.responseObject
import kotlinx.android.synthetic.main.activity_bus_service.*

class BusServiceActivity : AppCompatActivity() {

    private lateinit var txtBusStopTitle: TextView
    private lateinit var txtArrivalTime1: TextView
    private lateinit var txtArrivalTime2: TextView
    private lateinit var busStop: String
    private lateinit var recyclerView: RecyclerView
    private var shuttleList: ArrayList<BusServiceObject.ShuttleServiceResults.Shuttle> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bus_service)
        busStop = intent.getStringExtra("BusStopID")
        supportActionBar!!.title = intent.getStringExtra("BusStopName")

        recyclerView = findViewById(R.id.listBusTimings)
        recyclerView.layoutManager = LinearLayoutManager(this)

        getJsonData(busStop)

        busArrivalRefresh.setOnRefreshListener {
            // TODO create an abstraction for the function data loading function
            getJsonData(busStop)
            busArrivalRefresh.isRefreshing = false
        }

    }

    private fun getJsonData(busStop:String) {
        shuttleList.clear()
        FuelManager.instance.basePath = "https://nextbus.comfortdelgro.com.sg"
        Fuel.get("eventservice.svc/ShuttleService?busstopname=$busStop").responseObject<BusServiceObject> {_, _, result ->
            val(success, error) = result

            if(error == null) {
                shuttleList.clear()
                success!!.ShuttleServiceResult.shuttles.forEach {
                    item -> shuttleList.add(item)
                }
                recyclerView.adapter = BusServiceAdapter(this.shuttleList)
            } else {

            }
        }
    }
}
