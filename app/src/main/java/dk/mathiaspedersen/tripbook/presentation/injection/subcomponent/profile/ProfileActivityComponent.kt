package dk.mathiaspedersen.tripbook.presentation.injection.subcomponent.profile

import dagger.Subcomponent
import dk.mathiaspedersen.tripbook.presentation.activity.ProfileActivity
import dk.mathiaspedersen.tripbook.presentation.injection.scope.ActivityScope

@ActivityScope
@Subcomponent(modules = arrayOf(
        ProfileActivityModule::class
))
interface ProfileActivityComponent {
    fun injectTo(activity: ProfileActivity)
}