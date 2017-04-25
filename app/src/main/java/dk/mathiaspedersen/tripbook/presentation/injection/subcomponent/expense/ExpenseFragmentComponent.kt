package dk.mathiaspedersen.tripbook.presentation.injection.subcomponent.trips

import dagger.Subcomponent
import dk.mathiaspedersen.tripbook.presentation.fragment.ExpenseFragment
import dk.mathiaspedersen.tripbook.presentation.injection.scope.ActivityScope

@ActivityScope
@Subcomponent(modules = arrayOf(
        ExpenseFragmentModule::class
))
interface ExpenseFragmentComponent {
    fun injectTo(fragment: ExpenseFragment)
}