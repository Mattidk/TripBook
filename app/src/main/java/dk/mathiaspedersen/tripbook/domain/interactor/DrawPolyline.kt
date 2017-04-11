package dk.mathiaspedersen.tripbook.domain.interactor

import com.google.android.gms.maps.model.LatLngBounds
import com.google.maps.android.PolyUtil
import dk.mathiaspedersen.tripbook.domain.interactor.base.standard.Interactor
import dk.mathiaspedersen.tripbook.domain.interactor.event.DecodePathEvent
import dk.mathiaspedersen.tripbook.domain.interactor.event.Event
import dk.mathiaspedersen.tripbook.presentation.entity.TripDetail


class DrawPolyline : Interactor {

    var tripDetail: TripDetail? = null

    override fun invoke(): Event {
        val trip = this.tripDetail ?: throw IllegalStateException("Trip can´t be null")
        val path = PolyUtil.decode(trip.path)
        val bounds = LatLngBounds.builder()

        for (cord in path) {
            bounds.include(cord)
        }

        return DecodePathEvent(trip, path, bounds.build())
    }
}