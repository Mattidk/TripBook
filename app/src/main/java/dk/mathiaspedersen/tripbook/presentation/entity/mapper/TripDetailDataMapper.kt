package dk.mathiaspedersen.tripbook.presentation.entity.mapper

import dk.mathiaspedersen.tripbook.domain.entity.Location
import dk.mathiaspedersen.tripbook.domain.entity.Trip
import dk.mathiaspedersen.tripbook.domain.entity.Vehicle
import dk.mathiaspedersen.tripbook.presentation.entity.LocationDetail
import dk.mathiaspedersen.tripbook.presentation.entity.TripDetail
import dk.mathiaspedersen.tripbook.presentation.entity.VehicleDetail
import java.util.*

class TripDetailDataMapper {

    fun transform(trips: List<Trip>): List<TripDetail> {
        return trips.map { TripDetail(it.key, it.path, it.simplepath,
                VehicleDetail(it.vehicle.make, it.vehicle.model, it.vehicle.year, it.vehicle.odometer),
                it.purpose, it.distance,
                LocationDetail(it.departure.location, it.departure.latitude, it.departure.longitude, it.departure.timestamp),
                LocationDetail(it.destination.location, it.destination.latitude, it.destination.longitude, it.destination.timestamp)) }
    }

    fun transform(trips: ArrayList<TripDetail>): List<Trip> {
        return trips.map { Trip(it.key, it.path, it.simplepath, Vehicle(it.vehicle.make, it.vehicle.model,
                it.vehicle.year, it.vehicle.odometer), it.purpose, it.distance,
                Location(it.departure.location, it.departure.latitude, it.departure.longitude, it.departure.timestamp),
                Location(it.destination.location, it.destination.latitude, it.destination.longitude, it.destination.timestamp)) } as ArrayList
    }
}