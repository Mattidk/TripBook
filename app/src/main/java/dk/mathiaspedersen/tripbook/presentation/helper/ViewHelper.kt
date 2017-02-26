package dk.mathiaspedersen.tripbook.presentation.helper

interface ViewHelper {

    fun showProgress(message: Int)
    fun showProgress(message: Int, title: Int)

    fun showMessage(message: Int)
    fun showMessage(message: Int, title: Int)

    fun hideProgress()
    fun hideKeyboard()
}