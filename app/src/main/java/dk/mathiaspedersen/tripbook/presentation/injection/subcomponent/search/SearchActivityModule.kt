package dk.mathiaspedersen.tripbook.presentation.injection.subcomponent.search

import dagger.Module
import dagger.Provides
import dk.mathiaspedersen.tripbook.domain.interactor.base.Bus
import dk.mathiaspedersen.tripbook.presentation.activity.SearchActivity
import dk.mathiaspedersen.tripbook.presentation.injection.ActivityModule
import dk.mathiaspedersen.tripbook.presentation.injection.scope.ActivityScope
import dk.mathiaspedersen.tripbook.presentation.presenter.SearchPresenter
import dk.mathiaspedersen.tripbook.presentation.view.SearchView

@Module
class SearchActivityModule(activity: SearchActivity) : ActivityModule(activity) {

    @Provides @ActivityScope
    fun provideSearchView(): SearchView = activity as SearchView

    @Provides @ActivityScope
    fun provideSearchPresenter(view: SearchView, bus: Bus) =
            SearchPresenter(view, bus)

}