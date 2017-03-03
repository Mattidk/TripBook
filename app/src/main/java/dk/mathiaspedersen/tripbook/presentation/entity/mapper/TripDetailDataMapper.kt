package dk.mathiaspedersen.tripbook.presentation.entity.mapper

import dk.mathiaspedersen.tripbook.domain.entity.Trip
import dk.mathiaspedersen.tripbook.presentation.entity.TripDetail

class TripDetailDataMapper {

    fun transformTrips(trips: List<Trip>): List<TripDetail> {
        return trips.map { TripDetail(it.map) }
    }
}