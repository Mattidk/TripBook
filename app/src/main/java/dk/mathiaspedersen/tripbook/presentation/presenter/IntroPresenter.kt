package dk.mathiaspedersen.tripbook.presentation.presenter

import dk.mathiaspedersen.tripbook.presentation.view.IntroView

class IntroPresenter(override val view: IntroView) : BasePresenter<IntroView> {

    override fun dispose() {
        // Used this to clean up observables
    }
}