package dk.mathiaspedersen.tripbook.presentation.presenter

import dk.mathiaspedersen.tripbook.domain.interactor.base.Bus
import dk.mathiaspedersen.tripbook.domain.interactor.event.trip.GetTripsFailureEvent
import dk.mathiaspedersen.tripbook.presentation.view.ReportView
import org.greenrobot.eventbus.Subscribe

class ReportPresenter(
        override val view: ReportView,
        override val bus: Bus) : BasePresenter<ReportView> {

    @Subscribe
    fun onEvent(event: GetTripsFailureEvent) {
//        view.populateRecyclerView(event.populateRecyclerView)
    }
}