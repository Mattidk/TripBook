package dk.mathiaspedersen.tripbook.presentation.presenter

import dk.mathiaspedersen.tripbook.domain.interactor.ExampleInteractorImpl
import dk.mathiaspedersen.tripbook.domain.interactor.event.bus.Bus
import dk.mathiaspedersen.tripbook.domain.interactor.trip.TripInteractorExecutor
import dk.mathiaspedersen.tripbook.presentation.view.HostView

class HostPresenter(
        override val view: HostView,
        override val bus: Bus,
        val exampleInteractor: ExampleInteractorImpl,
        val interactorExecutor: TripInteractorExecutor) : BasePresenter<HostView>