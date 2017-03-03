package dk.mathiaspedersen.tripbook.presentation.view

import com.google.android.gms.maps.model.LatLng

interface DetailView: BaseView {
    fun drawPolyline(path : List<LatLng>)
}