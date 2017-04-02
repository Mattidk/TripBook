package dk.mathiaspedersen.tripbook.presentation.injection.subcomponent.about

import dagger.Module
import dagger.Provides
import dk.mathiaspedersen.tripbook.domain.interactor.base.Bus
import dk.mathiaspedersen.tripbook.presentation.activity.AboutActivity
import dk.mathiaspedersen.tripbook.presentation.injection.ActivityModule
import dk.mathiaspedersen.tripbook.presentation.injection.scope.ActivityScope
import dk.mathiaspedersen.tripbook.presentation.presenter.AboutPresenter
import dk.mathiaspedersen.tripbook.presentation.view.AboutView

@Module
class AboutActivityModule(activity: AboutActivity) : ActivityModule(activity) {

    @Provides @ActivityScope
    fun provideAboutView(): AboutView = activity as AboutView

    @Provides @ActivityScope
    fun provideAboutPresenter(view: AboutView, bus: Bus) =
            AboutPresenter(view, bus)
}