package dk.mathiaspedersen.tripbook.presentation.injection.subcomponent.trips

import dagger.Module
import dagger.Provides
import dk.mathiaspedersen.tripbook.domain.interactor.GetExampleFirebaseInteractor
import dk.mathiaspedersen.tripbook.domain.interactor.event.bus.Bus
import dk.mathiaspedersen.tripbook.domain.interactor.firebase.FirebaseInteractorExecutor
import dk.mathiaspedersen.tripbook.presentation.fragment.TripsFragment
import dk.mathiaspedersen.tripbook.presentation.injection.FragmentModule
import dk.mathiaspedersen.tripbook.presentation.presenter.TripsPresenter
import dk.mathiaspedersen.tripbook.presentation.view.MainView
import dk.mathiaspedersen.tripbook.presentation.view.TripsView

@Module
class TripsFragmentModule(fragment: TripsFragment) : FragmentModule(fragment) {

    @Provides
    fun provideTripsView(): TripsView = fragment as TripsView

    @Provides
    fun provideTripsPresenter(view: TripsView, bus: Bus,
                             exampleFirebaseInteractor: GetExampleFirebaseInteractor,
                             firebaseInteractorExecutor: FirebaseInteractorExecutor) = TripsPresenter(view, bus,
            exampleFirebaseInteractor, firebaseInteractorExecutor)

}