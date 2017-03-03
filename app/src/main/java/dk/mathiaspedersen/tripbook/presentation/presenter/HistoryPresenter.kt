package dk.mathiaspedersen.tripbook.presentation.presenter

import dk.mathiaspedersen.tripbook.domain.interactor.ExampleInteractorImpl
import dk.mathiaspedersen.tripbook.domain.interactor.event.bus.Bus
import dk.mathiaspedersen.tripbook.domain.interactor.event.trip.FetchTripsEvent
import dk.mathiaspedersen.tripbook.domain.interactor.trip.TripInteractorExecutor
import dk.mathiaspedersen.tripbook.presentation.view.HistoryView
import org.greenrobot.eventbus.Subscribe

class HistoryPresenter(
        override val view: HistoryView,
        override val bus: Bus,
        val exampleInteractor: ExampleInteractorImpl,
        val interactorExecutor: TripInteractorExecutor) : BasePresenter<HistoryView> {

    @Subscribe
    fun onEvent(event: FetchTripsEvent) {
//        view.populateRecyclerView(event.populateRecyclerView)
    }
}