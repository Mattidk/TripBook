package dk.mathiaspedersen.tripbook.presentation.view

import dk.mathiaspedersen.tripbook.presentation.entity.TripDetail

interface TripsView : BaseView {
    fun populateRecyclerView(trips: List<TripDetail>)
    fun unableToFetchTrips(message: String)
}