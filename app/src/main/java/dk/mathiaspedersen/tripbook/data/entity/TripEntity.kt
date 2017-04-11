package dk.mathiaspedersen.tripbook.data.entity

data class TripEntity(val key: String, val path: String, val simplepath: String, val vehicle: VehicleEntity, val purpose: String, val distance: Long, val departure: LocationEntity, val destination: LocationEntity)