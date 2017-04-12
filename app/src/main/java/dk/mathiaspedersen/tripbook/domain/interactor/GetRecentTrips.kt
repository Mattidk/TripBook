package dk.mathiaspedersen.tripbook.domain.interactor

import dk.mathiaspedersen.tripbook.domain.entity.Trip
import dk.mathiaspedersen.tripbook.domain.interactor.base.Bus
import dk.mathiaspedersen.tripbook.domain.interactor.base.firebase.FirebaseInteractor
import dk.mathiaspedersen.tripbook.domain.interactor.event.trip.GetRecentFailureEvent
import dk.mathiaspedersen.tripbook.domain.interactor.event.trip.GetRecentSuccessEvent
import dk.mathiaspedersen.tripbook.domain.repository.FirebaseRepository

class GetRecentTrips(val repository: FirebaseRepository, val bus: Bus): FirebaseInteractor {

    override fun invoke() {
        repository.getRecent(this)
    }

    fun onSuccess(response: List<Trip>) {
        bus.post(GetRecentSuccessEvent(response))
    }

    fun onFailure(message: String) {
        bus.post(GetRecentFailureEvent(message))
    }
}