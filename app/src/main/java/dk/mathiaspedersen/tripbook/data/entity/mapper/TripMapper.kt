package dk.mathiaspedersen.tripbook.data.entity.mapper

import dk.mathiaspedersen.tripbook.data.entity.TripEntity
import dk.mathiaspedersen.tripbook.domain.entity.Location
import dk.mathiaspedersen.tripbook.domain.entity.Trip
import dk.mathiaspedersen.tripbook.domain.entity.Vehicle
import java.util.*

class TripMapper {

    fun transform(trips: List<TripEntity>): List<Trip> {
        return trips.map { Trip(it.key, it.path, it.simplepath, Vehicle(it.vehicle.key, it.vehicle.icon, it.vehicle.make,
                it.vehicle.model, it.vehicle.year, it.vehicle.odometer), it.time, it.purpose, it.distance,
                Location(it.departure.location, it.departure.latitude, it.departure.longitude, it.departure.timestamp),
                Location(it.destination.location, it.destination.latitude, it.destination.longitude, it.destination.timestamp)) } as ArrayList
    }

    fun transform(trip: TripEntity): Trip {
        return Trip(trip.key, trip.path, trip.simplepath, Vehicle(trip.vehicle.key, trip.vehicle.icon, trip.vehicle.make,
                trip.vehicle.model, trip.vehicle.year, trip.vehicle.odometer), trip.time, trip.purpose, trip.distance,
                Location(trip.departure.location, trip.departure.latitude, trip.departure.longitude, trip.departure.timestamp),
                Location(trip.destination.location, trip.destination.latitude, trip.destination.longitude, trip.destination.timestamp))
    }
}