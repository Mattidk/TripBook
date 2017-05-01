package dk.mathiaspedersen.tripbook.presentation.view

import dk.mathiaspedersen.tripbook.presentation.entity.TripDetail
import java.util.*

interface TripsView : BaseView {
    fun populateRecyclerView(trips: ArrayList<TripDetail>)
    fun unableToFetchTrips(message: String)
    fun setTripText(message: String)
    fun setMilesText(message: String)
    fun setValueText(message: String)
    fun resetCounters(message: String)
}