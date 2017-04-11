package dk.mathiaspedersen.tripbook.presentation.injection.subcomponent.recent

import dagger.Subcomponent
import dk.mathiaspedersen.tripbook.presentation.fragment.RecentFragment
import dk.mathiaspedersen.tripbook.presentation.injection.scope.ActivityScope

@ActivityScope
@Subcomponent(modules = arrayOf(
        RecentFragmentModule::class
))
interface RecentFragmentComponent {
    fun injectTo(fragment: RecentFragment)
}