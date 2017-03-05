package dk.mathiaspedersen.tripbook.domain.interactor.event.trip

import dk.mathiaspedersen.tripbook.domain.interactor.event.Event

data class GetTripsSuccessEvent(val message: String) : Event