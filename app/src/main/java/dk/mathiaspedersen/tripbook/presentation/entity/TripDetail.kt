package dk.mathiaspedersen.tripbook.presentation.entity

import com.google.android.gms.maps.model.LatLng

data class TripDetail(val key: String, val start: LatLng, val end: LatLng, val map: String)