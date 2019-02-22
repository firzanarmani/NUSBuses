package com.example.firzanarmani.nusbuses

data class BusStopObject(
        val BusStopsResult: BusStopsResults
) {

    data class BusStopsResults(
            val busstops: List<Busstop>
    ) {

        data class Busstop(
                val caption: String,
                val latitude: Double,
                val longitude: Double,
                val name: String
        )
    }
}