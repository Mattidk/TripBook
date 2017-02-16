package dk.mathiaspedersen.tripbook.domain.interactor.base

interface Interactor {

    operator fun invoke(): Event
}