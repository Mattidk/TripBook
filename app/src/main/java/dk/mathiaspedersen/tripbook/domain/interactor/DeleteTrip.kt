package dk.mathiaspedersen.tripbook.domain.interactor

import dk.mathiaspedersen.tripbook.domain.interactor.base.BaseUseCase
import dk.mathiaspedersen.tripbook.domain.interactor.base.Params
import dk.mathiaspedersen.tripbook.domain.repository.TripRepository
import io.reactivex.Observable

class DeleteTrip(val repository: TripRepository) : BaseUseCase() {

    override fun getObservable(params: Params): Observable<String> {
        val key = params.getString("key", null)
        return repository.deleteTrip(key)
    }
}