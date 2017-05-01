package dk.mathiaspedersen.tripbook.presentation.presenter

import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import dk.mathiaspedersen.tripbook.domain.entity.Trip
import dk.mathiaspedersen.tripbook.domain.interactor.CalculateBounds
import dk.mathiaspedersen.tripbook.domain.interactor.GetTrip
import dk.mathiaspedersen.tripbook.domain.interactor.base.DefaultObserver
import dk.mathiaspedersen.tripbook.domain.interactor.base.Params
import dk.mathiaspedersen.tripbook.presentation.entity.TripDetail
import dk.mathiaspedersen.tripbook.presentation.entity.mapper.TripDetailDataMapper
import dk.mathiaspedersen.tripbook.presentation.view.DetailView

class DetailPresenter(override val view: DetailView, val getTrip: GetTrip,
                      val calculateBounds: CalculateBounds, val mapper: TripDetailDataMapper) : BasePresenter<DetailView> {

    fun getTrip(key: String) {
        val params = Params.create()
        params.putString("key", key)
        getTrip.execute(GetTripObserver(), params)
    }

    fun calculateBounds(trip: TripDetail) {
        val params = Params.create()
        params.putTripDetail("trip", trip)
        calculateBounds.execute(CalculateBoundsObserver(), params)
    }

    override fun dispose() {
        calculateBounds.dispose()
    }

    inner class GetTripObserver: DefaultObserver<Trip>() {

        override fun onNext(t: Trip) {
            super.onNext(t)
            calculateBounds(mapper.transform(t))
            view.setDestination(t.destination.location)
            view.setTime(t.time)
        }

        override fun onError(exception: Throwable) {
            super.onError(exception)
        }
    }

    inner class CalculateBoundsObserver: DefaultObserver<Triple<TripDetail, List<LatLng>, LatLngBounds>>() {
        override fun onNext(t: Triple<TripDetail, List<LatLng>, LatLngBounds>) {
            super.onNext(t)
            view.drawPolyline(t.first, t.second, t.third)
        }
    }
}