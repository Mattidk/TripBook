package dk.mathiaspedersen.tripbook.domain.interactor.event.manager

import dk.mathiaspedersen.tripbook.domain.interactor.event.Event

data class UnsuccessfulLoginEvent(val message: String) : Event