package dk.mathiaspedersen.tripbook.domain.interactor

import dk.mathiaspedersen.tripbook.domain.interactor.base.Bus
import dk.mathiaspedersen.tripbook.domain.interactor.event.manager.SignOutSuccessEvent
import dk.mathiaspedersen.tripbook.domain.interactor.event.manager.SignOutFailureEvent
import dk.mathiaspedersen.tripbook.domain.interactor.base.firebase.FirebaseInteractor
import dk.mathiaspedersen.tripbook.domain.manager.Manager

class SignOut(val manager: Manager, val bus: Bus) : FirebaseInteractor {

    override fun invoke() {
        manager.signOut(this)
    }

    fun onSuccess() {
        bus.post(SignOutSuccessEvent())
    }

    fun onFailure(message: String) {
        bus.post(SignOutFailureEvent())
    }
}