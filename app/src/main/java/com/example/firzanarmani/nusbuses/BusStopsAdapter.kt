package com.example.firzanarmani.nusbuses

import android.content.Context
import android.content.Intent
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_bus_stop.view.*

class BusStopsAdapter(private val items: ArrayList<BusStopObject.BusStopsResults.Busstop>) : RecyclerView.Adapter<BusStopsAdapter.BStopViewHolder>() {

    private lateinit var parentContext1 : Context
    // Create the view
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BStopViewHolder {
        val inflatedView = LayoutInflater.from(parent.context).inflate(R.layout.item_bus_stop, parent, false)
        val parentContext = parent.context
        parentContext1 = parentContext
        return BStopViewHolder(inflatedView)
    }

    // Replace contents of the view
    override fun onBindViewHolder(holder: BStopViewHolder, position: Int) {
        holder.txtBusStop.text = items[position].caption
        holder.layoutBusStop.setOnClickListener {
            val intent = Intent(parentContext1, BusServiceActivity::class.java).apply {
                putExtra("BusStopID", items[position].name)
                putExtra("BusStopName", items[position].caption)
            }
            parentContext1.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }


    class BStopViewHolder(v: View) : RecyclerView.ViewHolder(v){
        val txtBusStop = v.txtBusStopName!!
        val layoutBusStop = v.busStopLayout!!
    }

}