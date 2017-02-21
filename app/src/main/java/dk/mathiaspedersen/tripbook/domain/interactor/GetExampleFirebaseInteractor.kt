package dk.mathiaspedersen.tripbook.domain.interactor

import dk.mathiaspedersen.tripbook.domain.entity.Trip
import dk.mathiaspedersen.tripbook.domain.interactor.event.FirebaseErrorEvent
import dk.mathiaspedersen.tripbook.domain.interactor.event.FirebaseEvent
import dk.mathiaspedersen.tripbook.domain.interactor.event.bus.Bus
import dk.mathiaspedersen.tripbook.domain.interactor.firebase.FirebaseInteractor
import dk.mathiaspedersen.tripbook.domain.repository.ExampleFirebaseRepository

class GetExampleFirebaseInteractor(val exampleFirebaseRepository: ExampleFirebaseRepository,
                                   val bus: Bus) : FirebaseInteractor {

    override fun invoke() {
        exampleFirebaseRepository.getExample(this)
    }

    override fun successful(response: List<Trip>) {
        bus.post(FirebaseEvent(response))
    }

    override fun unsuccessful(message: String) {
        bus.post(FirebaseErrorEvent(message))
    }

}