package com.example.firzanarmani.nusbuses

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.item_bus_timings.view.*

class BusServiceAdapter(private val shuttleList: ArrayList<BusServiceObject.ShuttleServiceResults.Shuttle>) : RecyclerView.Adapter<BusServiceAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflatedView = LayoutInflater.from(parent.context).inflate(R.layout.item_bus_timings, parent, false)
        return ViewHolder(inflatedView)
    }

    override fun getItemCount(): Int {
        return shuttleList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val shuttle = shuttleList[position]
        holder.txtTitle.text = shuttle.name
        holder.txtArrival1.text = if(shuttle.arrivalTime == "Arr") { "Bus is arriving" } else if(shuttle.arrivalTime == "-"){ "Bus service is not in operation" } else { "Bus is arriving in ${shuttle.arrivalTime} minutes" }
        holder.txtArrival2.text = if(shuttle.nextArrivalTime == "-"){ "" } else {"Next bus arrives in ${shuttle.nextArrivalTime} minutes" }
    }

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val txtTitle: TextView = v.txtBusStopTitle
        val txtArrival1: TextView = v.txtArrivalTime1
        val txtArrival2: TextView = v.txtArrivalTime2
    }
}
