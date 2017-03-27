package dk.mathiaspedersen.tripbook.domain.interactor.event

import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import dk.mathiaspedersen.tripbook.presentation.entity.TripDetail

class DecodePathEvent(val trip: TripDetail, val path: MutableList<LatLng>, val bounds: LatLngBounds) : Event