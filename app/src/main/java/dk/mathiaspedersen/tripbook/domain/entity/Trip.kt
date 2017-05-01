package dk.mathiaspedersen.tripbook.domain.entity

import dk.mathiaspedersen.tripbook.presentation.util.staticmaps.map.StaticMap

data class Trip(val key: String, val path: String, val simplepath: StaticMap, val vehicle: Vehicle, val time: String, val purpose: String, val distance: Long, val departure: Location, val destination: Location)
