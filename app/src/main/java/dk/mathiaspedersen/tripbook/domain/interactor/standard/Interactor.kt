package dk.mathiaspedersen.tripbook.domain.interactor.standard

import dk.mathiaspedersen.tripbook.domain.interactor.event.Event

interface Interactor {

    operator fun invoke(): Event
}