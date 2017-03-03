package dk.mathiaspedersen.tripbook.data.entity.mapper

import dk.mathiaspedersen.tripbook.data.entity.TripModel
import dk.mathiaspedersen.tripbook.domain.entity.Trip

class TripMapper {

    fun transformTrips(trips: List<TripModel>): List<Trip> {
        return trips.map { Trip(it.map) }
    }

}