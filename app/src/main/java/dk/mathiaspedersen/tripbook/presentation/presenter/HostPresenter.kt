package dk.mathiaspedersen.tripbook.presentation.presenter

import dk.mathiaspedersen.tripbook.domain.interactor.ExampleInteractorImpl
import dk.mathiaspedersen.tripbook.domain.interactor.ManagerInteractorImpl
import dk.mathiaspedersen.tripbook.domain.interactor.event.bus.Bus
import dk.mathiaspedersen.tripbook.domain.interactor.event.manager.SignOutSuccessEvent
import dk.mathiaspedersen.tripbook.domain.interactor.event.manager.SignOutUnsuccessfulEvent
import dk.mathiaspedersen.tripbook.domain.interactor.event.trip.FirebaseEvent
import dk.mathiaspedersen.tripbook.domain.interactor.manager.ManagerInteractorExecutor
import dk.mathiaspedersen.tripbook.domain.interactor.trip.TripInteractorExecutor
import dk.mathiaspedersen.tripbook.presentation.view.HostView
import org.greenrobot.eventbus.Subscribe

class HostPresenter(
        override val view: HostView,
        override val bus: Bus,
        val exampleInteractor: ExampleInteractorImpl,
        val interactorExecutor: TripInteractorExecutor,
        val managerInteractor: ManagerInteractorImpl,
        val managerInteractorExecutor: ManagerInteractorExecutor) : BasePresenter<HostView> {

    @Subscribe
    fun onEvent(event: SignOutSuccessEvent) {
        view.signOutSuccessful()
    }

    @Subscribe
    fun onEvent(event: SignOutUnsuccessfulEvent) {
        view.signOutUnsuccessful()
    }

    fun signOut() {
        managerInteractorExecutor.signOut(managerInteractor)
    }

}