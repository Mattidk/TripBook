package dk.mathiaspedersen.tripbook.domain.interactor

import dk.mathiaspedersen.tripbook.domain.interactor.event.Event
import dk.mathiaspedersen.tripbook.domain.interactor.standard.Interactor
import dk.mathiaspedersen.tripbook.domain.interactor.event.ExampleEvent
import dk.mathiaspedersen.tripbook.domain.repository.ExampleRepository

class GetExampleInteractor(val exampleRepository: ExampleRepository) : Interactor {

    override fun invoke(): Event {
        val example = exampleRepository.getExample()
        return ExampleEvent(example)
    }
}