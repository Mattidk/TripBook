package dk.mathiaspedersen.tripbook.presentation.injection.subcomponent.login

import dagger.Module
import dagger.Provides
import dk.mathiaspedersen.tripbook.domain.interactor.GoogleSignIn
import dk.mathiaspedersen.tripbook.presentation.activity.LoginActivity
import dk.mathiaspedersen.tripbook.presentation.injection.ActivityModule
import dk.mathiaspedersen.tripbook.presentation.injection.scope.ActivityScope
import dk.mathiaspedersen.tripbook.presentation.presenter.LoginPresenter
import dk.mathiaspedersen.tripbook.presentation.view.LoginView

@Module
class LoginActivityModule(activity: LoginActivity) : ActivityModule(activity) {

    @Provides @ActivityScope
    fun provideLoginView(): LoginView = activity as LoginView

    @Provides @ActivityScope
    fun provideLoginPresenter(view: LoginView, googleSignIn: GoogleSignIn)
                             = LoginPresenter(view, googleSignIn)

}