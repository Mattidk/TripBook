package dk.mathiaspedersen.tripbook.presentation.injection.subcomponent.intro

import dagger.Module
import dagger.Provides
import dk.mathiaspedersen.tripbook.domain.interactor.base.Bus
import dk.mathiaspedersen.tripbook.presentation.activity.IntroActivity
import dk.mathiaspedersen.tripbook.presentation.injection.ActivityModule
import dk.mathiaspedersen.tripbook.presentation.injection.scope.ActivityScope
import dk.mathiaspedersen.tripbook.presentation.presenter.IntroPresenter
import dk.mathiaspedersen.tripbook.presentation.view.IntroView

@Module
class IntroActivityModule(activity: IntroActivity) : ActivityModule(activity) {

    @Provides @ActivityScope
    fun provideIntroView(): IntroView = activity as IntroView

    @Provides @ActivityScope
    fun provideIntroPresenter(view: IntroView, bus: Bus) = IntroPresenter(view, bus)

}