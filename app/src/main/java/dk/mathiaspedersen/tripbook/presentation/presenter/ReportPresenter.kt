package dk.mathiaspedersen.tripbook.presentation.presenter

import dk.mathiaspedersen.tripbook.presentation.view.ReportView

class ReportPresenter(override val view: ReportView) : BasePresenter<ReportView> {

    override fun dispose() {
        // Used this to clean up observables
    }
}