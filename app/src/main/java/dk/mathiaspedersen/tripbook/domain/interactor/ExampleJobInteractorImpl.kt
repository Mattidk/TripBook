package dk.mathiaspedersen.tripbook.domain.interactor

import dk.mathiaspedersen.tripbook.domain.interactor.event.Event
import dk.mathiaspedersen.tripbook.domain.interactor.event.trip.ExampleEvent
import dk.mathiaspedersen.tripbook.domain.interactor.standard.JobInteractor
import dk.mathiaspedersen.tripbook.domain.repository.ExampleJobRepository

class ExampleJobInteractorImpl(val exampleJobRepository: ExampleJobRepository) : JobInteractor {

    override fun invoke(): Event {
        val example = exampleJobRepository.getExample()
        return ExampleEvent(example)
    }
}