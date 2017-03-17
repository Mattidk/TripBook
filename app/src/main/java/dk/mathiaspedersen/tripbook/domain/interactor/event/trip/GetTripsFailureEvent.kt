package dk.mathiaspedersen.tripbook.domain.interactor.event.trip

import dk.mathiaspedersen.tripbook.domain.interactor.event.Event

data class GetTripsFailureEvent(val message: String) : Event