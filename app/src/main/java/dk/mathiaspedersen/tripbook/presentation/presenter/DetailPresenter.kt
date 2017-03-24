package dk.mathiaspedersen.tripbook.presentation.presenter

import dk.mathiaspedersen.tripbook.domain.interactor.DrawPolyline
import dk.mathiaspedersen.tripbook.domain.interactor.base.Bus
import dk.mathiaspedersen.tripbook.domain.interactor.base.standard.InteractorExecutor
import dk.mathiaspedersen.tripbook.domain.interactor.event.DecodePathEvent
import dk.mathiaspedersen.tripbook.presentation.view.DetailView
import org.greenrobot.eventbus.Subscribe

class DetailPresenter(
        override val view: DetailView,
        override val bus: Bus,
        val interactor: DrawPolyline,
        val interactorExecutor: InteractorExecutor) : BasePresenter<DetailView> {

    @Subscribe
    fun onEvent(event: DecodePathEvent) {
        view.drawPolyline(event.start, event.end, event.path, event.bounds)
    }

    fun prepareMap(path: String) {
        val interactorDetail = interactor
        interactorDetail.coordinates = path
        interactorExecutor.execute(interactorDetail)
    }

}