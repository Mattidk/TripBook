package dk.mathiaspedersen.tripbook.domain.repository

import dk.mathiaspedersen.tripbook.domain.interactor.firebase.FirebaseInteractor

interface ExampleFirebaseRepository {
    fun getExample(callback: FirebaseInteractor)
}