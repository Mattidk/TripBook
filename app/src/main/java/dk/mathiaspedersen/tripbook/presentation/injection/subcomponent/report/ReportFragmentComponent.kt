package dk.mathiaspedersen.tripbook.presentation.injection.subcomponent.report

import dagger.Subcomponent
import dk.mathiaspedersen.tripbook.presentation.fragment.ReportFragment
import dk.mathiaspedersen.tripbook.presentation.injection.scope.ActivityScope

@ActivityScope
@Subcomponent(modules = arrayOf(
        ReportFragmentModule::class
))
interface ReportFragmentComponent {
    fun injectTo(fragment: ReportFragment)
}