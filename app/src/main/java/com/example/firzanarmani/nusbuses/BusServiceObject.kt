package com.example.firzanarmani.nusbuses


data class BusServiceObject(
        val ShuttleServiceResult: ShuttleServiceResults
) {

    data class ShuttleServiceResults(
            val caption: String,
            val name: String,
            val shuttles: List<Shuttle>
    ) {

        data class Shuttle(
                val arrivalTime: String,
                val name: String,
                val nextArrivalTime: String,
                val nextPassengers: String,
                val passengers: String
        )
    }
}