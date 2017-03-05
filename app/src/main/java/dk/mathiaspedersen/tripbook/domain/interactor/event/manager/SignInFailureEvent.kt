package dk.mathiaspedersen.tripbook.domain.interactor.event.manager

import dk.mathiaspedersen.tripbook.domain.interactor.event.Event

data class SignInFailureEvent(val message: String) : Event