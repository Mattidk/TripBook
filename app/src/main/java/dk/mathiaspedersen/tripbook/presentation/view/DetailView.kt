package dk.mathiaspedersen.tripbook.presentation.view

import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds

interface DetailView: BaseView {
    fun drawPolyline(path: MutableList<LatLng>, bounds: LatLngBounds)
}