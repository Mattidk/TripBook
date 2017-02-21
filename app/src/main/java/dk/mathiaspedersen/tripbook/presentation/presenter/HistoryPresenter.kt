package dk.mathiaspedersen.tripbook.presentation.presenter

import dk.mathiaspedersen.tripbook.domain.interactor.GetExampleFirebaseInteractor
import dk.mathiaspedersen.tripbook.domain.interactor.event.ExampleEvent
import dk.mathiaspedersen.tripbook.domain.interactor.event.FirebaseEvent
import dk.mathiaspedersen.tripbook.domain.interactor.event.bus.Bus
import dk.mathiaspedersen.tripbook.domain.interactor.firebase.FirebaseInteractorExecutor
import dk.mathiaspedersen.tripbook.presentation.view.HistoryView
import org.greenrobot.eventbus.Subscribe

class HistoryPresenter(
        override val view: HistoryView,
        override val bus: Bus,
        val exampleFirebaseInteractor: GetExampleFirebaseInteractor,
        val firebaseInteractorExecutor: FirebaseInteractorExecutor) : Presenter<HistoryView> {

    @Subscribe
    fun onEvent(event: FirebaseEvent) {
        view.example(event.example)
    }
}