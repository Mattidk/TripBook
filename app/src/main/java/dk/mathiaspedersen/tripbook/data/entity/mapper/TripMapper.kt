package dk.mathiaspedersen.tripbook.data.entity.mapper

import dk.mathiaspedersen.tripbook.data.entity.TripEntity
import dk.mathiaspedersen.tripbook.domain.entity.Trip

class TripMapper {

    fun transformTrips(trips: List<TripEntity>): List<Trip> {
        return trips.map { Trip(it.key, it.map) }
    }
}