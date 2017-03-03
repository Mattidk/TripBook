package dk.mathiaspedersen.tripbook.presentation.presenter

import dk.mathiaspedersen.tripbook.domain.interactor.ExampleInteractorImpl
import dk.mathiaspedersen.tripbook.domain.interactor.event.bus.Bus
import dk.mathiaspedersen.tripbook.domain.interactor.event.trip.FetchTripsErrorEvent
import dk.mathiaspedersen.tripbook.domain.interactor.event.trip.FetchTripsEvent
import dk.mathiaspedersen.tripbook.domain.interactor.trip.TripInteractorExecutor
import dk.mathiaspedersen.tripbook.presentation.entity.mapper.TripDetailDataMapper
import dk.mathiaspedersen.tripbook.presentation.view.TripsView
import org.greenrobot.eventbus.Subscribe

class TripsPresenter(
        override val view: TripsView,
        override val bus: Bus,
        val interactor: ExampleInteractorImpl,
        val interactorExecutor: TripInteractorExecutor,
        val tripDataMapper: TripDetailDataMapper) : BasePresenter<TripsView> {

    @Subscribe
    fun onEvent(event: FetchTripsEvent) {
        view.populateRecyclerView(tripDataMapper.transformTrips(event.trips))
    }

    @Subscribe
    fun onEvent(event: FetchTripsErrorEvent) {
        view.unableToFetchTrips(event.message)
    }

    override fun onResume() {
        super.onResume()
        interactorExecutor.getTrips(interactor)
    }

    fun getUnclassifiedTrips() {
        interactorExecutor.getTrips(interactor)
    }
}