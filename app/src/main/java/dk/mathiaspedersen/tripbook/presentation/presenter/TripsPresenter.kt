package dk.mathiaspedersen.tripbook.presentation.presenter

import dk.mathiaspedersen.tripbook.domain.interactor.ExampleInteractorImpl
import dk.mathiaspedersen.tripbook.domain.interactor.event.bus.Bus
import dk.mathiaspedersen.tripbook.domain.interactor.event.trip.FirebaseErrorEvent
import dk.mathiaspedersen.tripbook.domain.interactor.event.trip.FirebaseEvent
import dk.mathiaspedersen.tripbook.domain.interactor.trip.TripInteractorExecutor
import dk.mathiaspedersen.tripbook.presentation.view.TripsView
import org.greenrobot.eventbus.Subscribe

class TripsPresenter(
        override val view: TripsView,
        override val bus: Bus,
        val interactor: ExampleInteractorImpl,
        val interactorExecutor: TripInteractorExecutor) : BasePresenter<TripsView> {

    @Subscribe
    fun onEvent(event: FirebaseEvent) {
        view.example(event.example)
    }

    @Subscribe
    fun onEvent(event: FirebaseErrorEvent) {
        view.onError(event.message)
    }

    override fun onResume() {
        super.onResume()
        interactorExecutor.getTrips(interactor)
    }

    fun getUnclassifiedTrips() {
        interactorExecutor.getTrips(interactor)
    }
}