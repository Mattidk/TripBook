package dk.mathiaspedersen.tripbook.presentation.presenter

import dk.mathiaspedersen.tripbook.presentation.view.ExpenseDetailView

class ExpenseDetailPresenter(override val view: ExpenseDetailView) : BasePresenter<ExpenseDetailView> {

    override fun dispose() {
        // Used this to clean up observables
    }
}