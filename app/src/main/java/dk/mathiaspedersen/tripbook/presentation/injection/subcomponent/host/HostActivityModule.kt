package dk.mathiaspedersen.tripbook.presentation.injection.subcomponent.host

import dagger.Module
import dagger.Provides
import dk.mathiaspedersen.tripbook.domain.interactor.SignOut
import dk.mathiaspedersen.tripbook.domain.interactor.base.Bus
import dk.mathiaspedersen.tripbook.domain.interactor.base.firebase.FirebaseInteractorExecutor
import dk.mathiaspedersen.tripbook.presentation.activity.HostActivity
import dk.mathiaspedersen.tripbook.presentation.fragment.HistoryFragment
import dk.mathiaspedersen.tripbook.presentation.fragment.TripsFragment
import dk.mathiaspedersen.tripbook.presentation.injection.ActivityModule
import dk.mathiaspedersen.tripbook.presentation.injection.scope.ActivityScope
import dk.mathiaspedersen.tripbook.presentation.presenter.HostPresenter
import dk.mathiaspedersen.tripbook.presentation.view.HostView

@Module
class HostActivityModule(activity: HostActivity) : ActivityModule(activity) {

    @Provides @ActivityScope
    fun provideHostView(): HostView = activity as HostView

    @Provides @ActivityScope
    fun provideHostPresenter(view: HostView, bus: Bus, interactor: SignOut, interactorExecutor:
                             FirebaseInteractorExecutor) = HostPresenter(view, bus, interactor,
                             interactorExecutor)

    @Provides @ActivityScope
    fun provideTripsFragment() = TripsFragment()

    @Provides @ActivityScope
    fun provideHistoryFragment() = HistoryFragment()

}