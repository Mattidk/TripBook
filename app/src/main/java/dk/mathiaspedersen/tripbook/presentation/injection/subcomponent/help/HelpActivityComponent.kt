package dk.mathiaspedersen.tripbook.presentation.injection.subcomponent.help

import dagger.Subcomponent
import dk.mathiaspedersen.tripbook.presentation.activity.HelpActivity
import dk.mathiaspedersen.tripbook.presentation.injection.scope.ActivityScope
import dk.mathiaspedersen.tripbook.presentation.injection.subcomponent.host.HelpActivityModule

@ActivityScope
@Subcomponent(modules = arrayOf(
        HelpActivityModule::class
))
interface HelpActivityComponent {
    fun injectTo(activity: HelpActivity)
}