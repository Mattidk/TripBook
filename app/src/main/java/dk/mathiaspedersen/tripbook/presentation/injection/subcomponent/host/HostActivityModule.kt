package dk.mathiaspedersen.tripbook.presentation.injection.subcomponent.host

import dagger.Module
import dagger.Provides
import dk.mathiaspedersen.tripbook.domain.interactor.ExampleInteractorImpl
import dk.mathiaspedersen.tripbook.domain.interactor.event.bus.Bus
import dk.mathiaspedersen.tripbook.domain.interactor.trip.TripInteractorExecutor
import dk.mathiaspedersen.tripbook.presentation.activity.HostActivity
import dk.mathiaspedersen.tripbook.presentation.fragment.HistoryFragment
import dk.mathiaspedersen.tripbook.presentation.fragment.TripsFragment
import dk.mathiaspedersen.tripbook.presentation.helper.ViewHelper
import dk.mathiaspedersen.tripbook.presentation.injection.ActivityModule
import dk.mathiaspedersen.tripbook.presentation.injection.scope.ActivityScope
import dk.mathiaspedersen.tripbook.presentation.presenter.HostPresenter
import dk.mathiaspedersen.tripbook.presentation.view.HostView

@Module
class HostActivityModule(activity: HostActivity) : ActivityModule(activity) {

    @Provides @ActivityScope
    fun provideMainView(): HostView = activity as HostView

    @Provides @ActivityScope
    fun provideMainPresenter(view: HostView, bus: Bus, helper: ViewHelper,
                             exampleInteractor: ExampleInteractorImpl,
                             interactorExecutor: TripInteractorExecutor) = HostPresenter(view, bus, helper,
            exampleInteractor, interactorExecutor)

    @Provides @ActivityScope
    fun provideTripsFragment() = TripsFragment()

    @Provides @ActivityScope
    fun provideHistoryFragment() = HistoryFragment()

}