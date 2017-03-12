package dk.mathiaspedersen.tripbook.presentation.injection.subcomponent.trips

import dagger.Module
import dagger.Provides
import dk.mathiaspedersen.tripbook.domain.interactor.GetUnclassifiedTrips
import dk.mathiaspedersen.tripbook.domain.interactor.base.Bus
import dk.mathiaspedersen.tripbook.domain.interactor.base.firebase.FirebaseInteractorExecutor
import dk.mathiaspedersen.tripbook.presentation.custom.TripAdapter
import dk.mathiaspedersen.tripbook.presentation.entity.TripDetail
import dk.mathiaspedersen.tripbook.presentation.entity.mapper.TripDetailDataMapper
import dk.mathiaspedersen.tripbook.presentation.fragment.TripsFragment
import dk.mathiaspedersen.tripbook.presentation.helper.AppSettings
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
    fun provideTripAdapter(settings: AppSettings) = TripAdapter(listOf<TripDetail>(), settings)

    @Provides
    fun provideTripsPresenter(view: TripsView, bus: Bus, interactor: GetUnclassifiedTrips,
                              interactorExecutor: FirebaseInteractorExecutor, tripDataMapper:
                              TripDetailDataMapper) = TripsPresenter(view, bus, interactor,
                              interactorExecutor, tripDataMapper)

}