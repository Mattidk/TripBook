package dk.mathiaspedersen.tripbook.domain.interactor

import dk.mathiaspedersen.tripbook.domain.entity.Trip
import dk.mathiaspedersen.tripbook.domain.interactor.event.bus.Bus
import dk.mathiaspedersen.tripbook.domain.interactor.event.trip.FirebaseErrorEvent
import dk.mathiaspedersen.tripbook.domain.interactor.event.trip.FirebaseEvent
import dk.mathiaspedersen.tripbook.domain.interactor.trip.TripInteractor
import dk.mathiaspedersen.tripbook.domain.repository.TripRepository

class ExampleInteractorImpl(val repository: TripRepository, val bus: Bus) : TripInteractor {

    override fun getTrips() {
        repository.getExample(this)
    }

    override fun successful(response: List<Trip>) {
        bus.post(FirebaseEvent(response))
    }

    override fun unsuccessful(message: String) {
        bus.post(FirebaseErrorEvent(message))
    }

}