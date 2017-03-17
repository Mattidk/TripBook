package dk.mathiaspedersen.tripbook.domain.interactor

import dk.mathiaspedersen.tripbook.domain.interactor.base.firebase.FirebaseInteractor
import dk.mathiaspedersen.tripbook.domain.repository.FirebaseRepository

class ClassifyBusinessTrip(val repository: FirebaseRepository): FirebaseInteractor {

    var key: String? = null

    override fun invoke() {
        repository.classifyBusinessTrip(key)
    }
}