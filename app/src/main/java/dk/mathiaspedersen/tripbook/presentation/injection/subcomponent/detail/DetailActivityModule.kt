package dk.mathiaspedersen.tripbook.presentation.injection.subcomponent.detail

import dagger.Module
import dagger.Provides
import dk.mathiaspedersen.tripbook.domain.interactor.DrawPolyline
import dk.mathiaspedersen.tripbook.domain.interactor.base.Bus
import dk.mathiaspedersen.tripbook.domain.interactor.base.standard.InteractorExecutor
import dk.mathiaspedersen.tripbook.presentation.activity.DetailActivity
import dk.mathiaspedersen.tripbook.presentation.injection.ActivityModule
import dk.mathiaspedersen.tripbook.presentation.injection.scope.ActivityScope
import dk.mathiaspedersen.tripbook.presentation.presenter.DetailPresenter
import dk.mathiaspedersen.tripbook.presentation.view.DetailView

@Module
class DetailActivityModule(activity: DetailActivity) : ActivityModule(activity) {

    @Provides @ActivityScope
    fun provideDetailView(): DetailView = activity as DetailView

    @Provides @ActivityScope
    fun provideDetailPresenter(view: DetailView, bus: Bus, interactor: DrawPolyline,
                               interactorExecutor: InteractorExecutor) =
                               DetailPresenter(view, bus, interactor, interactorExecutor)

}