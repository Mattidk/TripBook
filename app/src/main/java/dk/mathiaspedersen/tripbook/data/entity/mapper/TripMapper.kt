package dk.mathiaspedersen.tripbook.data.entity.mapper

import dk.mathiaspedersen.tripbook.data.entity.TripEntity
import dk.mathiaspedersen.tripbook.domain.entity.Trip
import java.util.*

class TripMapper {

    fun transform(trips: List<TripEntity>): List<Trip> {
        return trips.map { Trip(it.key, it.map) } as ArrayList
    }

    fun transform(trips: List<Trip>): ArrayList<TripEntity> {
        return trips.map { TripEntity(it.key, it.map) } as ArrayList
    }
}