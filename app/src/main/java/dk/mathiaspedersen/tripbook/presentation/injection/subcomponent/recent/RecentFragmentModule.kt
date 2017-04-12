package dk.mathiaspedersen.tripbook.presentation.injection.subcomponent.recent

import android.content.Context
import dagger.Module
import dagger.Provides
import dk.mathiaspedersen.tripbook.domain.interactor.GetRecentTrips
import dk.mathiaspedersen.tripbook.domain.interactor.base.Bus
import dk.mathiaspedersen.tripbook.domain.interactor.base.firebase.FirebaseInteractorExecutor
import dk.mathiaspedersen.tripbook.presentation.custom.RecentAdapter
import dk.mathiaspedersen.tripbook.presentation.custom.ViewHolderFactory
import dk.mathiaspedersen.tripbook.presentation.entity.TripDetail
import dk.mathiaspedersen.tripbook.presentation.entity.mapper.TripDetailDataMapper
import dk.mathiaspedersen.tripbook.presentation.fragment.RecentFragment
import dk.mathiaspedersen.tripbook.presentation.helper.AppSettings
import dk.mathiaspedersen.tripbook.presentation.injection.FragmentModule
import dk.mathiaspedersen.tripbook.presentation.presenter.RecentPresenter
import dk.mathiaspedersen.tripbook.presentation.view.RecentView

@Module
class RecentFragmentModule(fragment: RecentFragment) : FragmentModule(fragment) {

    @Provides
    fun provideRecentView(): RecentView = fragment as RecentView

    @Provides
    fun provideTripDataMapper() = TripDetailDataMapper()

    @Provides
    fun provideViewHolderFactory(context: Context, settings: AppSettings)
            = ViewHolderFactory(context, settings)

    @Provides
    fun provideRecentAdapter(context: Context, settings: AppSettings, viewHolderFactory: ViewHolderFactory)
            = RecentAdapter(arrayListOf<TripDetail>(), context, settings, viewHolderFactory)

    @Provides
    fun provideRecentPresenter(view: RecentView, bus: Bus, interactor: GetRecentTrips,
                               interactorExecutor: FirebaseInteractorExecutor, tripDataMapper:
                               TripDetailDataMapper) = RecentPresenter(view, bus, interactor, interactorExecutor, tripDataMapper)

}