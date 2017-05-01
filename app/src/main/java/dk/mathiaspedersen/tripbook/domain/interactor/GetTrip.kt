package dk.mathiaspedersen.tripbook.domain.interactor

import dk.mathiaspedersen.tripbook.domain.entity.Trip
import dk.mathiaspedersen.tripbook.domain.interactor.base.BaseUseCase
import dk.mathiaspedersen.tripbook.domain.interactor.base.Params
import dk.mathiaspedersen.tripbook.domain.repository.TripRepository
import io.reactivex.Observable

class GetTrip(val repository: TripRepository) : BaseUseCase() {

    override fun getObservable(params: Params): Observable<Trip> {
        val key = params.getString("key", null)
        return repository.getTrip(key)
    }
}