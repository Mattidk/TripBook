package dk.mathiaspedersen.tripbook.domain.interactor

import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.maps.android.PolyUtil
import dk.mathiaspedersen.tripbook.domain.interactor.base.BaseUseCase
import dk.mathiaspedersen.tripbook.domain.interactor.base.Params
import dk.mathiaspedersen.tripbook.presentation.entity.TripDetail
import io.reactivex.Observable

class CalculateBounds : BaseUseCase() {

    override fun getObservable(params: Params): Observable<Triple<TripDetail, List<LatLng>, LatLngBounds>> {
        return Observable.create { emitter ->

            val trip = params.getTripDetail("trip", null)

            val path = PolyUtil.decode(trip.path)
            val bounds = LatLngBounds.builder()

            for (cord in path) { bounds.include(cord) }

            emitter.onNext(Triple(trip, path, bounds.build()))
        }
    }
}