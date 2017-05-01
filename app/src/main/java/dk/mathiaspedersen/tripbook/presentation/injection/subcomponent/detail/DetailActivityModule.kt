package dk.mathiaspedersen.tripbook.presentation.injection.subcomponent.detail

import dagger.Module
import dagger.Provides
import dk.mathiaspedersen.tripbook.domain.interactor.CalculateBounds
import dk.mathiaspedersen.tripbook.domain.interactor.GetTrip
import dk.mathiaspedersen.tripbook.presentation.activity.DetailActivity
import dk.mathiaspedersen.tripbook.presentation.entity.mapper.TripDetailDataMapper
import dk.mathiaspedersen.tripbook.presentation.injection.ActivityModule
import dk.mathiaspedersen.tripbook.presentation.injection.scope.ActivityScope
import dk.mathiaspedersen.tripbook.presentation.presenter.DetailPresenter
import dk.mathiaspedersen.tripbook.presentation.view.DetailView

@Module
class DetailActivityModule(activity: DetailActivity) : ActivityModule(activity) {

    @Provides @ActivityScope
    fun provideDetailView(): DetailView = activity as DetailView

    @Provides
    fun provideTripDataMapper() = TripDetailDataMapper()

    @Provides @ActivityScope
    fun provideDetailPresenter(view: DetailView, getTrip: GetTrip, calculateBounds: CalculateBounds, mapper: TripDetailDataMapper)
            = DetailPresenter(view, getTrip, calculateBounds, mapper)

}