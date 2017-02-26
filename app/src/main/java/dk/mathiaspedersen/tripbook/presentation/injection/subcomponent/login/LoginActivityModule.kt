package dk.mathiaspedersen.tripbook.presentation.injection.subcomponent.login

import dagger.Module
import dagger.Provides
import dk.mathiaspedersen.tripbook.domain.interactor.ManagerInteractorImpl
import dk.mathiaspedersen.tripbook.domain.interactor.event.bus.Bus
import dk.mathiaspedersen.tripbook.domain.interactor.manager.ManagerInteractorExecutor
import dk.mathiaspedersen.tripbook.presentation.activity.LoginActivity
import dk.mathiaspedersen.tripbook.presentation.helper.ViewHelper
import dk.mathiaspedersen.tripbook.presentation.injection.ActivityModule
import dk.mathiaspedersen.tripbook.presentation.injection.scope.ActivityScope
import dk.mathiaspedersen.tripbook.presentation.presenter.LoginPresenter
import dk.mathiaspedersen.tripbook.presentation.view.LoginView

@Module
class LoginActivityModule(activity: LoginActivity) : ActivityModule(activity) {

    @Provides @ActivityScope
    fun provideLoginView(): LoginView = activity as LoginView

    @Provides @ActivityScope
    fun provideLoginPresenter(view: LoginView, bus: Bus, helper: ViewHelper,
                              managerInteractor: ManagerInteractorImpl,
                              managerInteractorExecutor: ManagerInteractorExecutor) = LoginPresenter(view, bus, helper,
            managerInteractor, managerInteractorExecutor)

}