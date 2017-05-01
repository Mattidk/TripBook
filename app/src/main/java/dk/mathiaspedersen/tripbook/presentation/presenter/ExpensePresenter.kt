package dk.mathiaspedersen.tripbook.presentation.presenter

import dk.mathiaspedersen.tripbook.presentation.view.ExpenseView

class ExpensePresenter(override val view: ExpenseView) : BasePresenter<ExpenseView> {

    override fun dispose() {
        // Used this to clean up observables
    }
}