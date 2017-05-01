package dk.mathiaspedersen.tripbook.presentation.presenter

interface BasePresenter<out T> {
    val view: T
    fun dispose()
}