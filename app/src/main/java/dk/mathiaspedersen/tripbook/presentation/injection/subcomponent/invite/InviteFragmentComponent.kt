package dk.mathiaspedersen.tripbook.presentation.injection.subcomponent.invite

import dagger.Subcomponent
import dk.mathiaspedersen.tripbook.presentation.fragment.InviteFragment
import dk.mathiaspedersen.tripbook.presentation.injection.scope.ActivityScope

@ActivityScope
@Subcomponent(modules = arrayOf(
        InviteFragmentModule::class
))
interface InviteFragmentComponent {
    fun injectTo(fragment: InviteFragment)
}