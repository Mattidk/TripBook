package dk.mathiaspedersen.tripbook.presentation.injection.subcomponent.trips

import dagger.Module
import dagger.Provides
import dk.mathiaspedersen.tripbook.domain.interactor.ExampleInteractorImpl
import dk.mathiaspedersen.tripbook.domain.interactor.event.bus.Bus
import dk.mathiaspedersen.tripbook.domain.interactor.trip.TripInteractorExecutor
import dk.mathiaspedersen.tripbook.presentation.entity.mapper.TripDetailDataMapper
import dk.mathiaspedersen.tripbook.presentation.fragment.TripsFragment
import dk.mathiaspedersen.tripbook.presentation.injection.FragmentModule
import dk.mathiaspedersen.tripbook.presentation.presenter.TripsPresenter
import dk.mathiaspedersen.tripbook.presentation.view.TripsView

@Module
class TripsFragmentModule(fragment: TripsFragment) : FragmentModule(fragment) {

    @Provides
    fun provideTripsView(): TripsView = fragment as TripsView

    @Provides
    fun provideTripDataMapper() = TripDetailDataMapper()

    @Provides
    fun provideTripsPresenter(view: TripsView, bus: Bus, exampleInteractor: ExampleInteractorImpl,
                              interactorExecutor: TripInteractorExecutor,
                              tripDataMapper: TripDetailDataMapper) = TripsPresenter(view, bus,
                              exampleInteractor, interactorExecutor, tripDataMapper)

}