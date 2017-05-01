package dk.mathiaspedersen.tripbook.data.entity

import dk.mathiaspedersen.tripbook.presentation.util.staticmaps.map.StaticMap

data class TripEntity(val key: String, val path: String, val simplepath: StaticMap, val vehicle: VehicleEntity, val time: String, val purpose: String, val distance: Long, val departure: LocationEntity, val destination: LocationEntity)