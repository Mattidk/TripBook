package dk.mathiaspedersen.tripbook.presentation.view

import dk.mathiaspedersen.tripbook.presentation.entity.TripDetail
import java.util.*

interface TripsView : BaseView {
    fun populateRecyclerView(trips: ArrayList<TripDetail>)
    fun unableToFetchTrips(message: String)
    fun displaySums(trips: Int, miles: Double, value: Double)
}