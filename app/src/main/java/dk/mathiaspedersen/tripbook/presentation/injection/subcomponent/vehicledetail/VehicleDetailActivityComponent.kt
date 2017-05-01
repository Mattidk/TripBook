package dk.mathiaspedersen.tripbook.presentation.injection.subcomponent.vehicledetail

import dagger.Subcomponent
import dk.mathiaspedersen.tripbook.presentation.activity.VehicleDetailActivity
import dk.mathiaspedersen.tripbook.presentation.injection.scope.ActivityScope

@ActivityScope
@Subcomponent(modules = arrayOf(
        VehicleDetailActivityModule::class
))
interface VehicleDetailActivityComponent {
    fun injectTo(activity: VehicleDetailActivity)
}