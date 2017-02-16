package dk.mathiaspedersen.tripbook.presentation.injection.subcomponent.main

import dagger.Module
import dagger.Provides
import dk.mathiaspedersen.tripbook.presentation.activity.MainActivity
import dk.mathiaspedersen.tripbook.domain.interactor.GetExampleInteractor
import dk.mathiaspedersen.tripbook.domain.interactor.base.Bus
import dk.mathiaspedersen.tripbook.domain.interactor.base.InteractorExecutor
import dk.mathiaspedersen.tripbook.presentation.injection.ActivityModule
import dk.mathiaspedersen.tripbook.presentation.injection.scope.ActivityScope
import dk.mathiaspedersen.tripbook.presentation.presenter.MainPresenter
import dk.mathiaspedersen.tripbook.presentation.view.MainView

@Module
class MainActivityModule(activity: MainActivity) : ActivityModule(activity) {

    @Provides @ActivityScope
    fun provideMainView(): MainView = activity as MainView

    @Provides @ActivityScope
    fun provideMainPresenter(view: MainView, bus: Bus,
                             exampleInteractor: GetExampleInteractor,
                             interactorExecutor: InteractorExecutor) = MainPresenter(view, bus,
            exampleInteractor, interactorExecutor)

}