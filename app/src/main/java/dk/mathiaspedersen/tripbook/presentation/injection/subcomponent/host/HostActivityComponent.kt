package dk.mathiaspedersen.tripbook.presentation.injection.subcomponent.host

import dagger.Subcomponent
import dk.mathiaspedersen.tripbook.presentation.activity.HostActivity
import dk.mathiaspedersen.tripbook.presentation.injection.scope.ActivityScope

@ActivityScope
@Subcomponent(modules = arrayOf(
        HostActivityModule::class
))
interface HostActivityComponent {

    fun injectTo(activity: HostActivity)
}