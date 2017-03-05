package dk.mathiaspedersen.tripbook.domain.interactor.event

import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds

class DecodePathEvent(val path: MutableList<LatLng>, val bounds: LatLngBounds) : Event