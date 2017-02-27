package dk.mathiaspedersen.tripbook.presentation.injection.subcomponent.history

import dagger.Module
import dagger.Provides
import dk.mathiaspedersen.tripbook.domain.interactor.ExampleInteractorImpl
import dk.mathiaspedersen.tripbook.domain.interactor.event.bus.Bus
import dk.mathiaspedersen.tripbook.domain.interactor.trip.TripInteractorExecutor
import dk.mathiaspedersen.tripbook.presentation.fragment.HistoryFragment
import dk.mathiaspedersen.tripbook.presentation.injection.FragmentModule
import dk.mathiaspedersen.tripbook.presentation.presenter.HistoryPresenter
import dk.mathiaspedersen.tripbook.presentation.view.HistoryView

@Module
class HistoryFragmentModule(fragment: HistoryFragment) : FragmentModule(fragment) {

    @Provides
    fun provideHistoryView(): HistoryView = fragment as HistoryView

    @Provides
    fun provideHistoryPresenter(view: HistoryView, bus: Bus, exampleInteractor: ExampleInteractorImpl,
                                interactorExecutor: TripInteractorExecutor) = HistoryPresenter(view, bus,
                                exampleInteractor, interactorExecutor)

}