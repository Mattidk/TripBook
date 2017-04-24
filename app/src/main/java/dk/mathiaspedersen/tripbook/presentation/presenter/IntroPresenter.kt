package dk.mathiaspedersen.tripbook.presentation.presenter

import dk.mathiaspedersen.tripbook.domain.interactor.base.Bus
import dk.mathiaspedersen.tripbook.domain.interactor.event.GetProfileSuccessEvent
import dk.mathiaspedersen.tripbook.presentation.view.IntroView
import org.greenrobot.eventbus.Subscribe

class IntroPresenter(
        override val view: IntroView,
        override val bus: Bus) : BasePresenter<IntroView> {

    @Subscribe
    fun onEvent(event: GetProfileSuccessEvent) {
        // Empty on purpose
    }

}