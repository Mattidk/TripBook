package dk.mathiaspedersen.tripbook.domain.interactor.event.trip

import dk.mathiaspedersen.tripbook.domain.interactor.event.Event

data class GetRecentFailureEvent(val message: String) : Event