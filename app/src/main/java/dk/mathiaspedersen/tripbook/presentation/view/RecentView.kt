package dk.mathiaspedersen.tripbook.presentation.view

import dk.mathiaspedersen.tripbook.presentation.entity.TripDetail
import java.util.*

interface RecentView : BaseView {
    fun populateRecyclerView(trips: ArrayList<TripDetail>)
    fun unableToFetchTrips(message: String)
}