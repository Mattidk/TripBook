package dk.mathiaspedersen.tripbook.presentation.presenter

import dk.mathiaspedersen.tripbook.domain.interactor.GetUnclassifiedTrips
import dk.mathiaspedersen.tripbook.domain.interactor.base.Bus
import dk.mathiaspedersen.tripbook.domain.interactor.base.firebase.FirebaseInteractorExecutor
import dk.mathiaspedersen.tripbook.domain.interactor.event.trip.GetTripsFailureEvent
import dk.mathiaspedersen.tripbook.domain.interactor.event.trip.GetTripsSuccessEvent
import dk.mathiaspedersen.tripbook.presentation.entity.mapper.TripDetailDataMapper
import dk.mathiaspedersen.tripbook.presentation.view.TripsView
import org.greenrobot.eventbus.Subscribe
import java.util.*

class TripsPresenter(
        override val view: TripsView,
        override val bus: Bus,
        val interactor: GetUnclassifiedTrips,
        val interactorExecutor: FirebaseInteractorExecutor,
        val tripDataMapper: TripDetailDataMapper) : BasePresenter<TripsView> {

    @Subscribe
    fun onEvent(event: GetTripsSuccessEvent) {
        val trips = tripDataMapper.transform(event.trips) as ArrayList
        val miles = trips.sumBy { it.distance.toInt() }.toLong() * 0.000621371192
        val value = miles * 0.54
        view.displaySums(trips.size, miles, value)
        view.populateRecyclerView(trips)
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