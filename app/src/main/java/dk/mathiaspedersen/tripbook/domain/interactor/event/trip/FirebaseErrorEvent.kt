package dk.mathiaspedersen.tripbook.domain.interactor.event.trip

import dk.mathiaspedersen.tripbook.domain.interactor.event.Event

data class FirebaseErrorEvent(val message: String) : Event