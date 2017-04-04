package dk.mathiaspedersen.tripbook.presentation.presenter

import dk.mathiaspedersen.tripbook.domain.interactor.base.Bus
import dk.mathiaspedersen.tripbook.domain.interactor.event.trip.GetTripsFailureEvent
import dk.mathiaspedersen.tripbook.presentation.view.SearchView
import org.greenrobot.eventbus.Subscribe

class SearchPresenter(
        override val view: SearchView,
        override val bus: Bus) : BasePresenter<SearchView> {

    @Subscribe
    fun onEvent(event: GetTripsFailureEvent) {
//        view.populateRecyclerView(event.populateRecyclerView)
    }
}