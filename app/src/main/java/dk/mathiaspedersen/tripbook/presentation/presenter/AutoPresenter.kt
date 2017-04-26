package dk.mathiaspedersen.tripbook.presentation.presenter

import dk.mathiaspedersen.tripbook.domain.interactor.base.Bus
import dk.mathiaspedersen.tripbook.domain.interactor.event.trip.GetTripsFailureEvent
import dk.mathiaspedersen.tripbook.presentation.view.AutoView
import dk.mathiaspedersen.tripbook.presentation.view.ExpenseView
import org.greenrobot.eventbus.Subscribe

class AutoPresenter(
        override val view: AutoView,
        override val bus: Bus) : BasePresenter<AutoView> {

    @Subscribe
    fun onEvent(event: GetTripsFailureEvent) {
//        view.populateRecyclerView(event.populateRecyclerView)
    }
}