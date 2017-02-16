package dk.mathiaspedersen.tripbook.presentation.injection.subcomponent.main

import dagger.Subcomponent
import dk.mathiaspedersen.tripbook.MainActivity
import dk.mathiaspedersen.tripbook.presentation.injection.scope.ActivityScope

@ActivityScope
@Subcomponent(modules = arrayOf(
        MainActivityModule::class
))
interface MainActivityComponent {

    fun injectTo(activity: MainActivity)
}