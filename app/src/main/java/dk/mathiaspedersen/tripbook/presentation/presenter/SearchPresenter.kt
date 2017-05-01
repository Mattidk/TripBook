package dk.mathiaspedersen.tripbook.presentation.presenter

import dk.mathiaspedersen.tripbook.presentation.view.SearchView

class SearchPresenter(override val view: SearchView) : BasePresenter<SearchView> {

    override fun dispose() {
        // Used this to clean up observables
    }
}