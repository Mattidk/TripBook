package dk.mathiaspedersen.tripbook.data.entity

import com.google.android.gms.maps.model.LatLng

data class TripEntity(val key: String, val start: LatLng, val end: LatLng, val map: String)