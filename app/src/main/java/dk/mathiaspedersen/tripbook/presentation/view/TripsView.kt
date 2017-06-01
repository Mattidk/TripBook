package dk.mathiaspedersen.tripbook.presentation.view

import dk.mathiaspedersen.tripbook.presentation.entity.TripDetail
import dk.mathiaspedersen.tripbook.presentation.entity.TripStackDetal
import java.util.*

interface TripsView : BaseView {
    fun populateRecyclerView(trips: ArrayList<TripDetail>)
    fun unableToFetchTrips(message: String)
    fun setTripText(message: String)
    fun setMilesText(message: String)
    fun setValueText(message: String)
    fun resetCounters(message: String)
    fun showMapDetail(key: String)
    fun showVehicleDetail(key: String, name: String)
    fun deleteTrip(key: String)
    fun changeVehicle(key: String)
    fun pushTrip(trip: TripStackDetal)
    fun popTrip(trip: TripStackDetal)
    fun showUndoSnackBar(description: String)
    fun hideUndoSnackBar()
}