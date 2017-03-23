package dk.mathiaspedersen.tripbook.presentation.entity.mapper

import dk.mathiaspedersen.tripbook.domain.entity.Trip
import dk.mathiaspedersen.tripbook.presentation.entity.TripDetail
import java.util.*

class TripDetailDataMapper {

    fun transform(trips: List<Trip>): List<TripDetail> {
        return trips.map { TripDetail(it.key, it.start, it.end, it.map) } as ArrayList
    }

    fun transform(trips: ArrayList<TripDetail>): List<Trip> {
        return trips.map { Trip(it.key, it.start, it.end, it.map) } as ArrayList
    }
}