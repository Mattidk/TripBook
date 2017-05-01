package dk.mathiaspedersen.tripbook.presentation.presenter

import dk.mathiaspedersen.tripbook.presentation.view.HelpView

class HelpPresenter(override val view: HelpView) : BasePresenter<HelpView> {

    override fun dispose() {
        // Used this to clean up observables
    }
}