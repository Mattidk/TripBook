package dk.mathiaspedersen.tripbook.domain.entity

data class Trip(val key: String, val path: String, val simplepath: String, val vehicle: Vehicle, val purpose: String, val distance: Long, val departure: Location, val destination: Location)
