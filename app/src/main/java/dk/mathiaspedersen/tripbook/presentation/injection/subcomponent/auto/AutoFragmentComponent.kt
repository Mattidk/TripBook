package dk.mathiaspedersen.tripbook.presentation.injection.subcomponent.auto

import dagger.Subcomponent
import dk.mathiaspedersen.tripbook.presentation.fragment.AutoFragment
import dk.mathiaspedersen.tripbook.presentation.injection.scope.ActivityScope

@ActivityScope
@Subcomponent(modules = arrayOf(
        AutoFragmentModule::class
))
interface AutoFragmentComponent {
    fun injectTo(fragment: AutoFragment)
}