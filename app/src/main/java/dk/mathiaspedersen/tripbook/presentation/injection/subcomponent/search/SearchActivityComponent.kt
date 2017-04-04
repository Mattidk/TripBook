package dk.mathiaspedersen.tripbook.presentation.injection.subcomponent.search

import dagger.Subcomponent
import dk.mathiaspedersen.tripbook.presentation.activity.SearchActivity
import dk.mathiaspedersen.tripbook.presentation.injection.scope.ActivityScope

@ActivityScope
@Subcomponent(modules = arrayOf(
        SearchActivityModule::class
))
interface SearchActivityComponent {
    fun injectTo(activity: SearchActivity)
}