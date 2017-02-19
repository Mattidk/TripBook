package dk.mathiaspedersen.tripbook.presentation.injection.subcomponent.container

import dagger.Subcomponent
import dk.mathiaspedersen.tripbook.presentation.activity.ContainerActivity
import dk.mathiaspedersen.tripbook.presentation.injection.scope.ActivityScope

@ActivityScope
@Subcomponent(modules = arrayOf(
        ContainerActivityModule::class
))
interface ContainerActivityComponent {

    fun injectTo(activity: ContainerActivity)
}