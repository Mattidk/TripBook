package dk.mathiaspedersen.tripbook.presentation.presenter

import dk.mathiaspedersen.tripbook.presentation.view.AboutView

class AboutPresenter(override val view: AboutView) : BasePresenter<AboutView> {

    override fun dispose() {
        // Used this to clean up observables
    }
}