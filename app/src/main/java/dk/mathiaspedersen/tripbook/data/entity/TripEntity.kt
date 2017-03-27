package dk.mathiaspedersen.tripbook.data.entity

import com.google.android.gms.maps.model.LatLng
import java.util.*

data class TripEntity(val key: String, val time: Date, val start: LatLng, val end: LatLng, val map: String)