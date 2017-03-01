package dk.mathiaspedersen.tripbook.domain.interactor.event.trip

import dk.mathiaspedersen.tripbook.domain.interactor.event.Event
import dk.mathiaspedersen.tripbook.presentation.model.EncodedTrip

data class FirebaseEvent(val example: List<EncodedTrip>) : Event