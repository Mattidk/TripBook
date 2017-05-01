package dk.mathiaspedersen.tripbook.presentation.presenter

import dk.mathiaspedersen.tripbook.presentation.view.AutoView

class AutoPresenter(override val view: AutoView) : BasePresenter<AutoView> {

    override fun dispose() {
        // Used this to clean up observables
    }
}