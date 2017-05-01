package dk.mathiaspedersen.tripbook.presentation.presenter

import dk.mathiaspedersen.tripbook.presentation.view.VehicleDetailView

class VehicleDetailPresenter(override val view: VehicleDetailView) : BasePresenter<VehicleDetailView> {

    override fun dispose() {
        // Used this to clean up observables
    }

    fun test() {
        view.test()
    }
}