package dk.mathiaspedersen.tripbook.presentation.presenter

import dk.mathiaspedersen.tripbook.presentation.view.InviteView

class InvitePresenter(override val view: InviteView) : BasePresenter<InviteView> {

    override fun dispose() {
        // Used this to clean up observables
    }
}