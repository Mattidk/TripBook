package dk.mathiaspedersen.tripbook.presentation.presenter

import dk.mathiaspedersen.tripbook.domain.interactor.base.Bus
import dk.mathiaspedersen.tripbook.domain.interactor.event.trip.GetTripsFailureEvent
import dk.mathiaspedersen.tripbook.presentation.view.ExpenseView
import org.greenrobot.eventbus.Subscribe

class ExpensePresenter(
        override val view: ExpenseView,
        override val bus: Bus) : BasePresenter<ExpenseView> {

    @Subscribe
    fun onEvent(event: GetTripsFailureEvent) {
//        view.populateRecyclerView(event.populateRecyclerView)
    }
}