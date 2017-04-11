package dk.mathiaspedersen.tripbook.data.entity.mapper

import dk.mathiaspedersen.tripbook.data.entity.LocationEntity
import dk.mathiaspedersen.tripbook.data.entity.TripEntity
import dk.mathiaspedersen.tripbook.data.entity.VehicleEntity
import dk.mathiaspedersen.tripbook.domain.entity.Location
import dk.mathiaspedersen.tripbook.domain.entity.Trip
import dk.mathiaspedersen.tripbook.domain.entity.Vehicle
import java.util.*

class TripMapper {

    fun transform(trips: List<TripEntity>): List<Trip> {
        return trips.map { Trip(it.key, it.path, it.simplepath, Vehicle(it.vehicle.make, it.vehicle.model, it.vehicle.year, it.vehicle.odometer), it.purpose, it.distance,
                Location(it.departure.location, it.departure.latitude, it.departure.longitude, it.departure.timestamp),
                Location(it.destination.location, it.destination.latitude, it.destination.longitude, it.destination.timestamp)) } as ArrayList
    }

    fun transform(trips: List<Trip>): ArrayList<TripEntity> {
        return trips.map { TripEntity(it.key, it.path, it.simplepath, VehicleEntity(it.vehicle.make, it.vehicle.model, it.vehicle.year, it.vehicle.odometer), it.purpose, it.distance,
                LocationEntity(it.departure.location, it.departure.latitude, it.departure.longitude, it.departure.timestamp),
                LocationEntity(it.destination.location, it.destination.latitude, it.destination.longitude, it.destination.timestamp)) } as ArrayList
    }
}