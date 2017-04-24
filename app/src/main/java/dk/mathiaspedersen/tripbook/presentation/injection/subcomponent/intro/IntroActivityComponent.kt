package dk.mathiaspedersen.tripbook.presentation.injection.subcomponent.intro

import dagger.Subcomponent
import dk.mathiaspedersen.tripbook.presentation.activity.IntroActivity
import dk.mathiaspedersen.tripbook.presentation.injection.scope.ActivityScope

@ActivityScope
@Subcomponent(modules = arrayOf(
        IntroActivityModule::class
))
interface IntroActivityComponent {
    fun injectTo(activity: IntroActivity)
}