package dk.mathiaspedersen.tripbook.presentation.presenter

import dk.mathiaspedersen.tripbook.domain.interactor.base.Bus
import dk.mathiaspedersen.tripbook.domain.interactor.event.trip.GetTripsFailureEvent
import dk.mathiaspedersen.tripbook.presentation.view.AboutView
import dk.mathiaspedersen.tripbook.presentation.view.ExpenseView
import dk.mathiaspedersen.tripbook.presentation.view.InviteView
import org.greenrobot.eventbus.Subscribe

class InvitePresenter(
        override val view: InviteView,
        override val bus: Bus) : BasePresenter<InviteView> {

    @Subscribe
    fun onEvent(event: GetTripsFailureEvent) {
//        view.populateRecyclerView(event.populateRecyclerView)
    }
}