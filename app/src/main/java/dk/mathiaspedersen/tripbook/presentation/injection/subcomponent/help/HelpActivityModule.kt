package dk.mathiaspedersen.tripbook.presentation.injection.subcomponent.host

import dagger.Module
import dagger.Provides
import dk.mathiaspedersen.tripbook.domain.interactor.base.Bus
import dk.mathiaspedersen.tripbook.presentation.activity.HelpActivity
import dk.mathiaspedersen.tripbook.presentation.injection.ActivityModule
import dk.mathiaspedersen.tripbook.presentation.injection.scope.ActivityScope
import dk.mathiaspedersen.tripbook.presentation.presenter.HelpPresenter
import dk.mathiaspedersen.tripbook.presentation.view.HelpView

@Module
class HelpActivityModule(activity: HelpActivity) : ActivityModule(activity) {

    @Provides @ActivityScope
    fun provideHelpView(): HelpView = activity as HelpView

    @Provides @ActivityScope
    fun provideHelpPresenter(view: HelpView, bus: Bus) = HelpPresenter(view, bus)

}