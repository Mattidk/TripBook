package dk.mathiaspedersen.tripbook.presentation.presenter

import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import dk.mathiaspedersen.tripbook.domain.interactor.base.DefaultObserver
import dk.mathiaspedersen.tripbook.domain.interactor.GoogleSignIn
import dk.mathiaspedersen.tripbook.domain.interactor.base.Params
import dk.mathiaspedersen.tripbook.presentation.view.LoginView

class LoginPresenter(override val view: LoginView, val googleSignIn: GoogleSignIn) : BasePresenter<LoginView> {

    fun signInWithGoogle(account: GoogleSignInAccount) {
        val params = Params.create()
        params.putGoogleSignInAccount("account", account)
        googleSignIn.execute(GoogleSignInObserver(), params)
    }

    override fun dispose() {
        googleSignIn.dispose()
    }

    inner class GoogleSignInObserver : DefaultObserver<GoogleSignInAccount>() {
        override fun onNext(t: GoogleSignInAccount) {
            super.onNext(t)
            view.successfulLogin()
        }

        override fun onComplete() {
            super.onComplete()
        }

        override fun onError(exception: Throwable) {
            super.onError(exception)
        }
    }
}