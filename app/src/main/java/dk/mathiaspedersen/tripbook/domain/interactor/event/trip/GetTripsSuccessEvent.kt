package dk.mathiaspedersen.tripbook.domain.interactor.event.trip

import dk.mathiaspedersen.tripbook.domain.entity.Trip
import dk.mathiaspedersen.tripbook.domain.interactor.event.Event

data class GetTripsSuccessEvent(val trips: List<Trip>) : Event