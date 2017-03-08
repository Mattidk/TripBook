package dk.mathiaspedersen.tripbook.presentation.presenter

import dk.mathiaspedersen.tripbook.domain.interactor.base.Bus
import dk.mathiaspedersen.tripbook.domain.interactor.event.trip.GetTripsFailureEvent
import dk.mathiaspedersen.tripbook.presentation.view.SettingsView
import org.greenrobot.eventbus.Subscribe

class SettingsPresenter(
        override val view: SettingsView,
        override val bus: Bus) : BasePresenter<SettingsView> {

    @Subscribe
    fun onEvent(event: GetTripsFailureEvent) {
//        view.populateRecyclerView(tripDataMapper.transformTrips(event.trips))
    }
}