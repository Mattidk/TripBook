package dk.mathiaspedersen.tripbook.presentation.presenter

import dk.mathiaspedersen.tripbook.domain.interactor.GetExampleFirebaseInteractor
import dk.mathiaspedersen.tripbook.domain.interactor.event.ExampleEvent
import dk.mathiaspedersen.tripbook.domain.interactor.event.FirebaseEvent
import dk.mathiaspedersen.tripbook.domain.interactor.event.bus.Bus
import dk.mathiaspedersen.tripbook.domain.interactor.firebase.FirebaseInteractorExecutor
import dk.mathiaspedersen.tripbook.presentation.view.MainView
import org.greenrobot.eventbus.Subscribe

class MainPresenter(
        override val view: MainView,
        override val bus: Bus,
        val exampleFirebaseInteractor: GetExampleFirebaseInteractor,
        val firebaseInteractorExecutor: FirebaseInteractorExecutor) : Presenter<MainView> {

    @Subscribe
    fun onEvent(event: ExampleEvent) {
        view.example(event.example)
    }

    @Subscribe
    fun onEvent(event: FirebaseEvent) {
        view.example(event.example)
    }

    override fun onResume() {
        super.onResume()
        firebaseInteractorExecutor.execute(exampleFirebaseInteractor)
    }
}