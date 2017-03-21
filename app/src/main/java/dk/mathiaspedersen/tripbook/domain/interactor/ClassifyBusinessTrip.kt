package dk.mathiaspedersen.tripbook.domain.interactor

import dk.mathiaspedersen.tripbook.domain.entity.Trip
import dk.mathiaspedersen.tripbook.domain.interactor.base.firebase.FirebaseInteractor
import dk.mathiaspedersen.tripbook.domain.repository.FirebaseRepository

class ClassifyBusinessTrip(val repository: FirebaseRepository): FirebaseInteractor {

    var list: List<Trip>? = null

    override fun invoke() {
        val list = this.list ?: throw IllegalStateException("list can´t be null")
        repository.classifyBusinessTrip(list)
    }
}