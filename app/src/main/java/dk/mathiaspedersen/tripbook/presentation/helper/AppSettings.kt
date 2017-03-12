package dk.mathiaspedersen.tripbook.presentation.helper

import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.PolylineOptions
import dk.mathiaspedersen.tripbook.presentation.util.staticmaps.map.StaticMap.Path.Style

interface AppSettings {
    fun getSelectedTheme(): Int
    fun preventExit(): Boolean
    fun getMapStyle(): Int
    fun getStaticMapStyle(): String
    fun getPolylineStyle(path: MutableList<LatLng>): PolylineOptions
    fun getStaticPolylineStyle(): Style
}