package dk.mathiaspedersen.tripbook.domain.entity

import com.google.android.gms.maps.model.LatLng
import java.util.*

data class Trip(val key: String, val time: Date, val start: LatLng, val end: LatLng, val map: String)
