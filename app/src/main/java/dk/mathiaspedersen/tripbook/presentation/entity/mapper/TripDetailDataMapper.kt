package dk.mathiaspedersen.tripbook.presentation.entity.mapper

import dk.mathiaspedersen.tripbook.domain.entity.Trip
import dk.mathiaspedersen.tripbook.presentation.entity.TripDetail
import java.util.*

class TripDetailDataMapper {

    fun transformTrips(trips: List<Trip>): ArrayList<TripDetail> {
        return ArrayList(trips.map { TripDetail(it.key, it.map) })
    }
}