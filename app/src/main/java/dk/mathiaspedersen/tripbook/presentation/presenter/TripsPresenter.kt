package dk.mathiaspedersen.tripbook.presentation.presenter

import dk.mathiaspedersen.tripbook.domain.interactor.GetExampleFirebaseInteractor
import dk.mathiaspedersen.tripbook.domain.interactor.event.ExampleEvent
import dk.mathiaspedersen.tripbook.domain.interactor.event.FirebaseErrorEvent
import dk.mathiaspedersen.tripbook.domain.interactor.event.FirebaseEvent
import dk.mathiaspedersen.tripbook.domain.interactor.event.bus.Bus
import dk.mathiaspedersen.tripbook.domain.interactor.firebase.FirebaseInteractorExecutor
import dk.mathiaspedersen.tripbook.presentation.view.MainView
import dk.mathiaspedersen.tripbook.presentation.view.TripsView
import org.greenrobot.eventbus.Subscribe

class TripsPresenter(
        override val view: TripsView,
        override val bus: Bus,
        val exampleFirebaseInteractor: GetExampleFirebaseInteractor,
        val firebaseInteractorExecutor: FirebaseInteractorExecutor) : Presenter<TripsView> {

    @Subscribe
    fun onEvent(event: FirebaseEvent) {
        view.example(event.example)
    }

    @Subscribe
    fun onEvent(event: FirebaseErrorEvent) {
        view.onError(event.message)
    }

    override fun onResume() {
        super.onResume()
        firebaseInteractorExecutor.execute(exampleFirebaseInteractor)
    }

    fun getUnclassifiedTrips() {
        firebaseInteractorExecutor.execute(exampleFirebaseInteractor)
    }
}