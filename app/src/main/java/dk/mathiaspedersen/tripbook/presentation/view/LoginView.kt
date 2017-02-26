package dk.mathiaspedersen.tripbook.presentation.view

interface LoginView : BaseView {
    fun successfulLogin()
    fun unsuccessfullogin(message: String)
}