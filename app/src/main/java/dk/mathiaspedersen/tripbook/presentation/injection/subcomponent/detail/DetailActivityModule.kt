package dk.mathiaspedersen.tripbook.presentation.injection.subcomponent.detail

import dagger.Module
import dagger.Provides
import dk.mathiaspedersen.tripbook.domain.interactor.ExampleInteractorImpl
import dk.mathiaspedersen.tripbook.domain.interactor.ManagerInteractorImpl
import dk.mathiaspedersen.tripbook.domain.interactor.event.bus.Bus
import dk.mathiaspedersen.tripbook.domain.interactor.manager.ManagerInteractorExecutor
import dk.mathiaspedersen.tripbook.domain.interactor.trip.TripInteractorExecutor
import dk.mathiaspedersen.tripbook.presentation.activity.DetailActivity
import dk.mathiaspedersen.tripbook.presentation.activity.HostActivity
import dk.mathiaspedersen.tripbook.presentation.fragment.HistoryFragment
import dk.mathiaspedersen.tripbook.presentation.fragment.TripsFragment
import dk.mathiaspedersen.tripbook.presentation.injection.ActivityModule
import dk.mathiaspedersen.tripbook.presentation.injection.scope.ActivityScope
import dk.mathiaspedersen.tripbook.presentation.presenter.DetailPresenter
import dk.mathiaspedersen.tripbook.presentation.presenter.HostPresenter
import dk.mathiaspedersen.tripbook.presentation.view.DetailView
import dk.mathiaspedersen.tripbook.presentation.view.HostView

@Module
class DetailActivityModule(activity: DetailActivity) : ActivityModule(activity) {

    @Provides @ActivityScope
    fun provideDetailView(): DetailView = activity as DetailView

    @Provides @ActivityScope
    fun provideDetailPresenter(view: DetailView, bus: Bus,
                               exampleInteractor: ExampleInteractorImpl,
                               interactorExecutor: TripInteractorExecutor) = DetailPresenter(view, bus,
            exampleInteractor, interactorExecutor)
}