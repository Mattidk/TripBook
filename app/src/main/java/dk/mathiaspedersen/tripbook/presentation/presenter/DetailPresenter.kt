package dk.mathiaspedersen.tripbook.presentation.presenter

import dk.mathiaspedersen.tripbook.domain.interactor.ExampleInteractorImpl
import dk.mathiaspedersen.tripbook.domain.interactor.event.bus.Bus
import dk.mathiaspedersen.tripbook.domain.interactor.event.manager.SignOutSuccessEvent
import dk.mathiaspedersen.tripbook.domain.interactor.trip.TripInteractorExecutor
import dk.mathiaspedersen.tripbook.presentation.view.DetailView
import org.greenrobot.eventbus.Subscribe

class DetailPresenter(
        override val view: DetailView,
        override val bus: Bus,
        val exampleInteractor: ExampleInteractorImpl,
        val interactorExecutor: TripInteractorExecutor) : BasePresenter<DetailView> {

    @Subscribe
    fun onEvent(event: SignOutSuccessEvent) {
//        view.drawPolyline()
    }

}