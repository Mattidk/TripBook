package dk.mathiaspedersen.tripbook.presentation.presenter

import dk.mathiaspedersen.tripbook.domain.interactor.SignOut
import dk.mathiaspedersen.tripbook.domain.interactor.base.Bus
import dk.mathiaspedersen.tripbook.domain.interactor.event.manager.SignOutSuccessEvent
import dk.mathiaspedersen.tripbook.domain.interactor.event.manager.SignOutFailureEvent
import dk.mathiaspedersen.tripbook.domain.interactor.base.firebase.FirebaseInteractorExecutor
import dk.mathiaspedersen.tripbook.presentation.view.HostView
import org.greenrobot.eventbus.Subscribe

class HostPresenter(
        override val view: HostView,
        override val bus: Bus,
        val interactor: SignOut,
        val interactorExecutor: FirebaseInteractorExecutor) : BasePresenter<HostView> {

    @Subscribe
    fun onEvent(event: SignOutSuccessEvent) {
        view.signOutSuccessful()
    }

    @Subscribe
    fun onEvent(event: SignOutFailureEvent) {
        view.signOutUnsuccessful()
    }

    fun signOut() {
        interactorExecutor.execute(interactor)
    }

}