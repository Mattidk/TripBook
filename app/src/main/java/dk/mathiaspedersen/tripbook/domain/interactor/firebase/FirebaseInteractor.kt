package dk.mathiaspedersen.tripbook.domain.interactor.firebase

import dk.mathiaspedersen.tripbook.domain.entity.Trip

interface FirebaseInteractor {
    operator fun invoke()
    fun successful(response: List<Trip>)
    fun unsuccessful(message: String)
}