package dk.mathiaspedersen.tripbook.domain.interactor.standard

import dk.mathiaspedersen.tripbook.domain.interactor.event.Event

interface JobInteractor {

    operator fun invoke(): Event
}