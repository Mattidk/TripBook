package dk.mathiaspedersen.tripbook.presentation.presenter

import dk.mathiaspedersen.tripbook.domain.interactor.base.Bus

interface BasePresenter<out T> {

    val view: T
    val bus: Bus

    fun onResume() {
        bus.register(this)
    }

    fun onPause() {
        bus.unregister(this)
    }
}