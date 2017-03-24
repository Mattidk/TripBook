package dk.mathiaspedersen.tripbook.presentation.view

import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds

interface DetailView: BaseView {
    fun drawPolyline(start: LatLng, end: LatLng, path: MutableList<LatLng>, bounds: LatLngBounds)
}