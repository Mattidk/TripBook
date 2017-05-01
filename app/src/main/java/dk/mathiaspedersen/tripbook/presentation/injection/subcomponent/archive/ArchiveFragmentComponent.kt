package dk.mathiaspedersen.tripbook.presentation.injection.subcomponent.archive

import dagger.Subcomponent
import dk.mathiaspedersen.tripbook.presentation.fragment.ArchiveFragment
import dk.mathiaspedersen.tripbook.presentation.injection.scope.ActivityScope

@ActivityScope
@Subcomponent(modules = arrayOf(
        ArchiveFragmentModule::class
))
interface ArchiveFragmentComponent {
    fun injectTo(fragment: ArchiveFragment)
}