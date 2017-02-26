package dk.mathiaspedersen.tripbook.presentation.injection.subcomponent.login

import dagger.Subcomponent
import dk.mathiaspedersen.tripbook.presentation.activity.LoginActivity
import dk.mathiaspedersen.tripbook.presentation.injection.scope.ActivityScope

@ActivityScope
@Subcomponent(modules = arrayOf(
        LoginActivityModule::class
))
interface LoginActivityComponent {

    fun injectTo(activity: LoginActivity)

}