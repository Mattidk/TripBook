package dk.mathiaspedersen.tripbook.presentation.injection.subcomponent.history

import dagger.Subcomponent
import dk.mathiaspedersen.tripbook.presentation.fragment.HistoryFragment
import dk.mathiaspedersen.tripbook.presentation.injection.scope.ActivityScope

@ActivityScope
@Subcomponent(modules = arrayOf(
        HistoryFragmentModule::class
))
interface HistoryFragmentComponent {

    fun injectTo(fragment: HistoryFragment)
}