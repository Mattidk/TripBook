package dk.mathiaspedersen.tripbook.presentation.injection.subcomponent.container

import dagger.Module
import dagger.Provides
import dk.mathiaspedersen.tripbook.domain.interactor.GetExampleFirebaseInteractor
import dk.mathiaspedersen.tripbook.domain.interactor.event.bus.Bus
import dk.mathiaspedersen.tripbook.domain.interactor.firebase.FirebaseInteractorExecutor
import dk.mathiaspedersen.tripbook.presentation.activity.ContainerActivity
import dk.mathiaspedersen.tripbook.presentation.fragment.HistoryFragment
import dk.mathiaspedersen.tripbook.presentation.fragment.TripsFragment
import dk.mathiaspedersen.tripbook.presentation.injection.ActivityModule
import dk.mathiaspedersen.tripbook.presentation.injection.scope.ActivityScope
import dk.mathiaspedersen.tripbook.presentation.presenter.MainPresenter
import dk.mathiaspedersen.tripbook.presentation.view.MainView

@Module
class ContainerActivityModule(activity: ContainerActivity) : ActivityModule(activity) {

    @Provides @ActivityScope
    fun provideMainView(): MainView = activity as MainView

    @Provides @ActivityScope
    fun provideMainPresenter(view: MainView, bus: Bus,
                             exampleFirebaseInteractor: GetExampleFirebaseInteractor,
                             firebaseInteractorExecutor: FirebaseInteractorExecutor) = MainPresenter(view, bus,
            exampleFirebaseInteractor, firebaseInteractorExecutor)

    @Provides @ActivityScope
    fun provideTripsFragment() = TripsFragment()

    @Provides @ActivityScope
    fun provideHistoryFragment() = HistoryFragment()

}