package dk.mathiaspedersen.tripbook.presentation.injection.subcomponent.trips

import dagger.Subcomponent
import dk.mathiaspedersen.tripbook.presentation.fragment.TripsFragment
import dk.mathiaspedersen.tripbook.presentation.injection.scope.ActivityScope

@ActivityScope
@Subcomponent(modules = arrayOf(
        TripsFragmentModule::class
))
interface TripsFragmentComponent {

    fun injectTo(fragment: TripsFragment)
}