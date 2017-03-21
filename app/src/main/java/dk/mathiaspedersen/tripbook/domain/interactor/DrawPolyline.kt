package dk.mathiaspedersen.tripbook.domain.interactor

import com.google.android.gms.maps.model.LatLngBounds
import com.google.maps.android.PolyUtil
import dk.mathiaspedersen.tripbook.domain.interactor.base.standard.Interactor
import dk.mathiaspedersen.tripbook.domain.interactor.event.DecodePathEvent
import dk.mathiaspedersen.tripbook.domain.interactor.event.Event


class DrawPolyline : Interactor {

    var coordinates: String? = null

    override fun invoke(): Event {
        val coordinates = this.coordinates ?: throw IllegalStateException("Coordinates can´t be null")
        val path = PolyUtil.decode(coordinates)
        val bounds = LatLngBounds.builder()

        if (path.isNotEmpty() && path.size >= 2) {

            for (cord in path) {
                bounds.include(cord)
            }

            return DecodePathEvent(path, bounds.build())
        }
        return DecodePathEvent(path, bounds.build())
    }
}