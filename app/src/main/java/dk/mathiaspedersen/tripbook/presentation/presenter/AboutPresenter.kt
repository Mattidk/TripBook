package dk.mathiaspedersen.tripbook.presentation.presenter

import dk.mathiaspedersen.tripbook.domain.interactor.base.Bus
import dk.mathiaspedersen.tripbook.domain.interactor.event.trip.GetTripsFailureEvent
import dk.mathiaspedersen.tripbook.presentation.view.AboutView
import org.greenrobot.eventbus.Subscribe

class AboutPresenter(
        override val view: AboutView,
        override val bus: Bus) : BasePresenter<AboutView> {

    @Subscribe
    fun onEvent(event: GetTripsFailureEvent) {
//        view.populateRecyclerView(event.populateRecyclerView)
    }
}