package dk.mathiaspedersen.tripbook.presentation.injection.subcomponent.about

import dagger.Subcomponent
import dk.mathiaspedersen.tripbook.presentation.activity.AboutActivity
import dk.mathiaspedersen.tripbook.presentation.injection.scope.ActivityScope

@ActivityScope
@Subcomponent(modules = arrayOf(
        AboutActivityModule::class
))
interface AboutActivityComponent {
    fun injectTo(activity: AboutActivity)
}