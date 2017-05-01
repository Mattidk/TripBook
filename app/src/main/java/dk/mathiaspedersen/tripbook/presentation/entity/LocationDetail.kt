package dk.mathiaspedersen.tripbook.presentation.entity

import com.google.android.gms.maps.model.LatLng


class LocationDetail(val location: String, val latitude: String, val longitude: String, val timestamp: Long) {

    fun getLocation(): LatLng {
        return LatLng(latitude.toDouble(), longitude.toDouble())
    }
}