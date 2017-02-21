package dk.mathiaspedersen.tripbook.domain.interactor.event

import dk.mathiaspedersen.tripbook.domain.entity.Trip

data class FirebaseEvent(val example: List<Trip>) : Event