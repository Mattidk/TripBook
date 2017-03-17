package dk.mathiaspedersen.tripbook.presentation.presenter

import dk.mathiaspedersen.tripbook.domain.interactor.GetUnclassifiedTrips
import dk.mathiaspedersen.tripbook.domain.interactor.base.Bus
import dk.mathiaspedersen.tripbook.domain.interactor.base.firebase.FirebaseInteractorExecutor
import dk.mathiaspedersen.tripbook.domain.interactor.event.trip.GetTripsFailureEvent
import dk.mathiaspedersen.tripbook.domain.interactor.event.trip.GetTripsSuccessEvent
import dk.mathiaspedersen.tripbook.presentation.entity.mapper.TripDetailDataMapper
import dk.mathiaspedersen.tripbook.presentation.view.TripsView
import org.greenrobot.eventbus.Subscribe

class TripsPresenter(
        override val view: TripsView,
        override val bus: Bus,
        val interactor: GetUnclassifiedTrips,
        val interactorExecutor: FirebaseInteractorExecutor,
        val tripDataMapper: TripDetailDataMapper) : BasePresenter<TripsView> {

    @Subscribe
    fun onEvent(event: GetTripsSuccessEvent) {
        view.populateRecyclerView(tripDataMapper.transformTrips(event.trips))
    }

    @Subscribe
    fun onEvent(event: GetTripsFailureEvent) {
        view.unableToFetchTrips(event.message)
    }

    override fun onResume() {
        super.onResume()
        interactorExecutor.execute(interactor)
    }

    fun getUnclassifiedTrips() {
        interactorExecutor.execute(interactor)
    }
}