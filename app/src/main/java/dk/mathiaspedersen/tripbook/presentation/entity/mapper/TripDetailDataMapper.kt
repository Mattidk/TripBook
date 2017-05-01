package dk.mathiaspedersen.tripbook.presentation.entity.mapper

import dk.mathiaspedersen.tripbook.domain.entity.Trip
import dk.mathiaspedersen.tripbook.presentation.entity.LocationDetail
import dk.mathiaspedersen.tripbook.presentation.entity.TripDetail
import dk.mathiaspedersen.tripbook.presentation.entity.VehicleDetail

class TripDetailDataMapper {

    fun transform(trips: List<Trip>): List<TripDetail> {
        return trips.map { TripDetail(it.key, it.path, it.simplepath,
                VehicleDetail(it.vehicle.key, it.vehicle.icon, it.vehicle.make, it.vehicle.model, it.vehicle.year, it.vehicle.odometer), it.time, it.purpose, it.distance,
                LocationDetail(it.departure.location, it.departure.latitude, it.departure.longitude, it.departure.timestamp),
                LocationDetail(it.destination.location, it.destination.latitude, it.destination.longitude, it.destination.timestamp)) }
    }

    fun transform(trip: Trip): TripDetail {
        return TripDetail(trip.key, trip.path, trip.simplepath,
                VehicleDetail(trip.vehicle.key, trip.vehicle.icon, trip.vehicle.make, trip.vehicle.model, trip.vehicle.year, trip.vehicle.odometer), trip.time, trip.purpose, trip.distance,
                LocationDetail(trip.departure.location, trip.departure.latitude, trip.departure.longitude, trip.departure.timestamp),
                LocationDetail(trip.destination.location, trip.destination.latitude, trip.destination.longitude, trip.destination.timestamp))

    }
}