package dk.mathiaspedersen.tripbook.presentation.presenter

import dk.mathiaspedersen.tripbook.presentation.view.ArchiveView

class ArchivePresenter(override val view: ArchiveView) : BasePresenter<ArchiveView> {

    override fun dispose() {
        // Used this to clean up observables
    }
}