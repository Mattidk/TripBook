package dk.mathiaspedersen.tripbook.domain.entity

import com.google.android.gms.maps.model.LatLng

data class Trip(val key: String, val start: LatLng, val end: LatLng, val map: String)
