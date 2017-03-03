package dk.mathiaspedersen.tripbook.presentation.injection.subcomponent.detail

import dagger.Subcomponent
import dk.mathiaspedersen.tripbook.presentation.activity.DetailActivity
import dk.mathiaspedersen.tripbook.presentation.injection.scope.ActivityScope

@ActivityScope
@Subcomponent(modules = arrayOf(
        DetailActivityModule::class
))
interface DetailActivityComponent {

    fun injectTo(activity: DetailActivity)

}