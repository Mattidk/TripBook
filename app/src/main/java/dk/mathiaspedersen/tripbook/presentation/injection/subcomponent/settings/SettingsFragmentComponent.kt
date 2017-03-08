package dk.mathiaspedersen.tripbook.presentation.injection.subcomponent.settings

import dagger.Subcomponent
import dk.mathiaspedersen.tripbook.presentation.fragment.SettingsFragment
import dk.mathiaspedersen.tripbook.presentation.injection.scope.ActivityScope

@ActivityScope
@Subcomponent(modules = arrayOf(
        SettingsFragmentModule::class
))
interface SettingsFragmentComponent {
    fun injectTo(fragment: SettingsFragment)
}