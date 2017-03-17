package dk.mathiaspedersen.tripbook.domain.interactor

import dk.mathiaspedersen.tripbook.domain.entity.Trip
import dk.mathiaspedersen.tripbook.domain.interactor.base.Bus
import dk.mathiaspedersen.tripbook.domain.interactor.base.firebase.FirebaseInteractor
import dk.mathiaspedersen.tripbook.domain.interactor.event.trip.GetTripsFailureEvent
import dk.mathiaspedersen.tripbook.domain.interactor.event.trip.GetTripsSuccessEvent
import dk.mathiaspedersen.tripbook.domain.repository.FirebaseRepository

class GetUnclassifiedTrips(val repository: FirebaseRepository, val bus: Bus): FirebaseInteractor {

    override fun invoke() {
        repository.getTrips(this)
    }

    fun onSuccess(response: List<Trip>) {
        bus.post(GetTripsSuccessEvent(response))
    }

    fun onFailure(message: String) {
        bus.post(GetTripsFailureEvent(message))
    }
}