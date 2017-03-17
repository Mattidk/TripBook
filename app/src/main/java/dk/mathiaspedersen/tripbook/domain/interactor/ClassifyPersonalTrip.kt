package dk.mathiaspedersen.tripbook.domain.interactor

import dk.mathiaspedersen.tripbook.domain.interactor.base.firebase.FirebaseInteractor
import dk.mathiaspedersen.tripbook.domain.repository.FirebaseRepository

class ClassifyPersonalTrip(val repository: FirebaseRepository): FirebaseInteractor {

    var key: String? = null

    override fun invoke() {
        repository.classifyPersonalTrip(key)
    }
}