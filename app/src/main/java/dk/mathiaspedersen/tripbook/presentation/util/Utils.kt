package dk.mathiaspedersen.tripbook.presentation.util

import android.content.Context
import android.graphics.drawable.Drawable
import com.amulyakhare.textdrawable.TextDrawable
import com.amulyakhare.textdrawable.util.ColorGenerator
import dk.mathiaspedersen.tripbook.data.entity.model.FirebaseTrip
import dk.mathiaspedersen.tripbook.data.entity.model.FirebaseVehicle
import dk.mathiaspedersen.tripbook.presentation.helper.AppSettings
import dk.mathiaspedersen.tripbook.presentation.util.staticmaps.map.StaticMap

class Utils(val context: Context, val settings: AppSettings){

    fun createStaticMap(model: FirebaseTrip): StaticMap {
        return StaticMap().path(settings.getStaticPolylineStyle(), model.simplepath)
                .style(settings.getStaticMapStyle())
                .marker(StaticMap.Marker.Style.FLAT_GREEN.toBuilder().label('A').build(), StaticMap.GeoPoint(model.departure.latitude.toDouble(), model.departure.longitude.toDouble()))
                .marker(StaticMap.Marker.Style.FLAT_RED.toBuilder().label('B').build(), StaticMap.GeoPoint(model.destination.latitude.toDouble(), model.destination.longitude.toDouble()))
    }

    fun createIcon(model: FirebaseVehicle): Drawable {
        val letter = model.make[0].toString()
        val color = ColorGenerator.MATERIAL.getColor(letter)
        val builder = TextDrawable.builder().round()
        return builder.build(letter, color)
    }
}