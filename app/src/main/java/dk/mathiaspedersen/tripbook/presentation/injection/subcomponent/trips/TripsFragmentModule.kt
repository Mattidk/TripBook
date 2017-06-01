package dk.mathiaspedersen.tripbook.presentation.injection.subcomponent.trips

import android.content.Context
import dagger.Module
import dagger.Provides
import dk.mathiaspedersen.tripbook.domain.interactor.GetTrips
import dk.mathiaspedersen.tripbook.presentation.custom.TripAdapter
import dk.mathiaspedersen.tripbook.presentation.custom.ViewHolderFactory
import dk.mathiaspedersen.tripbook.presentation.entity.TripDetail
import dk.mathiaspedersen.tripbook.presentation.entity.TripStackDetal
import dk.mathiaspedersen.tripbook.presentation.entity.mapper.TripDetailDataMapper
import dk.mathiaspedersen.tripbook.presentation.fragment.TripsFragment
import dk.mathiaspedersen.tripbook.presentation.helper.AppSettings
import dk.mathiaspedersen.tripbook.presentation.injection.FragmentModule
import dk.mathiaspedersen.tripbook.presentation.presenter.TripsPresenter
import dk.mathiaspedersen.tripbook.presentation.view.TripsView
import java.util.*

@Module
class TripsFragmentModule(fragment: TripsFragment) : FragmentModule(fragment) {

    @Provides
    fun provideTripsView(): TripsView = fragment as TripsView

    @Provides
    fun provideTripDataMapper() = TripDetailDataMapper()

    @Provides
    fun provideViewHolderFactory(context: Context, settings: AppSettings)
            = ViewHolderFactory(context, settings)

    @Provides
    fun provideTripAdapter(view: TripsView, context: Context, settings: AppSettings, viewHolderFactory: ViewHolderFactory)
            = TripAdapter(view, arrayListOf<TripDetail>(), context, settings, viewHolderFactory)

    @Provides
    fun provideTripsPresenter(view: TripsView, context: Context, getTrips: GetTrips, mapper: TripDetailDataMapper)
            = TripsPresenter(view, context, getTrips, ArrayDeque<TripStackDetal>(), mapper)

}