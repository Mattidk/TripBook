package dk.mathiaspedersen.tripbook.presentation.injection.subcomponent.hostSettings

import dagger.Subcomponent
import dk.mathiaspedersen.tripbook.presentation.activity.SettingsActivity
import dk.mathiaspedersen.tripbook.presentation.injection.scope.ActivityScope

@ActivityScope
@Subcomponent(modules = arrayOf(
        HostSettingsActivityModule::class
))
interface HostSettingsActivityComponent {
    fun injectTo(activity: SettingsActivity)
}