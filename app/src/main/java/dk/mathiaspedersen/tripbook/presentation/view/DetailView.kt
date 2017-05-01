package dk.mathiaspedersen.tripbook.presentation.view

import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import dk.mathiaspedersen.tripbook.presentation.entity.TripDetail

interface DetailView : BaseView {
    fun drawPolyline(trip: TripDetail, path: List<LatLng>, bounds: LatLngBounds)
    fun setDestination(destination: String)
    fun setTime(time: String)
}