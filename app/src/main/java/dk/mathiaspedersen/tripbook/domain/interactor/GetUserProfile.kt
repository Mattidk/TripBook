package dk.mathiaspedersen.tripbook.domain.interactor

import dk.mathiaspedersen.tripbook.domain.entity.User
import dk.mathiaspedersen.tripbook.domain.interactor.base.Bus
import dk.mathiaspedersen.tripbook.domain.interactor.base.firebase.FirebaseInteractor
import dk.mathiaspedersen.tripbook.domain.interactor.event.GetProfileFailureEvent
import dk.mathiaspedersen.tripbook.domain.interactor.event.GetProfileSuccessEvent
import dk.mathiaspedersen.tripbook.domain.manager.Manager

class GetUserProfile(val manager: Manager, val bus: Bus): FirebaseInteractor {

    override fun invoke() {
        manager.getUserProfile(this)
    }

    fun onSuccess(user: User) {
        bus.post(GetProfileSuccessEvent(user))
    }

    fun onFailure(message: String) {
        bus.post(GetProfileFailureEvent(message))
    }

}