package dk.mathiaspedersen.tripbook.presentation.presenter

import dk.mathiaspedersen.tripbook.domain.interactor.GetRecentTrips
import dk.mathiaspedersen.tripbook.domain.interactor.base.Bus
import dk.mathiaspedersen.tripbook.domain.interactor.base.firebase.FirebaseInteractorExecutor
import dk.mathiaspedersen.tripbook.domain.interactor.event.trip.GetRecentFailureEvent
import dk.mathiaspedersen.tripbook.domain.interactor.event.trip.GetRecentSuccessEvent
import dk.mathiaspedersen.tripbook.presentation.entity.mapper.TripDetailDataMapper
import dk.mathiaspedersen.tripbook.presentation.view.RecentView
import org.greenrobot.eventbus.Subscribe
import java.util.*

class RecentPresenter(
        override val view: RecentView,
        override val bus: Bus,
        val interactor: GetRecentTrips,
        val interactorExecutor: FirebaseInteractorExecutor,
        val tripDataMapper: TripDetailDataMapper) : BasePresenter<RecentView> {

    @Subscribe
    fun onEvent(event: GetRecentSuccessEvent) {
        view.populateRecyclerView(tripDataMapper.transform(event.trips).reversed() as ArrayList)
    }

    @Subscribe
    fun onEvent(event: GetRecentFailureEvent) {
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