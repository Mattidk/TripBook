package dk.mathiaspedersen.tripbook.presentation.presenter

import dk.mathiaspedersen.tripbook.domain.interactor.base.Bus
import dk.mathiaspedersen.tripbook.domain.interactor.event.ExampleEvent
import dk.mathiaspedersen.tripbook.presentation.view.MainView
import org.greenrobot.eventbus.Subscribe

class MainPresenter(
        override val view: MainView,
        override val bus: Bus) : Presenter<MainView> {

    @Subscribe
    fun onEvent(event: ExampleEvent) {
    }

    fun exampleClicked() {
        view.example()
    }

}