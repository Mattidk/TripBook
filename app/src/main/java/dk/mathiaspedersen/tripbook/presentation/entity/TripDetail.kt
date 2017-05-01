package dk.mathiaspedersen.tripbook.presentation.entity

import dk.mathiaspedersen.tripbook.presentation.util.staticmaps.map.StaticMap

class TripDetail(val key: String, val path: String, val simplepath: StaticMap, val vehicle: VehicleDetail,
                 val time: String, val purpose: String, val distance: Long, val departure: LocationDetail,
                 val destination: LocationDetail)