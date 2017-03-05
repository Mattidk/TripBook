package dk.mathiaspedersen.tripbook.presentation.presenter

import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import dk.mathiaspedersen.tripbook.domain.interactor.SignInWithGoogle
import dk.mathiaspedersen.tripbook.domain.interactor.base.Bus
import dk.mathiaspedersen.tripbook.domain.interactor.base.firebase.FirebaseInteractorExecutor
import dk.mathiaspedersen.tripbook.domain.interactor.event.manager.SignInFailureEvent
import dk.mathiaspedersen.tripbook.domain.interactor.event.manager.SignInSuccessEvent
import dk.mathiaspedersen.tripbook.presentation.view.LoginView
import org.greenrobot.eventbus.Subscribe

class LoginPresenter(
        override val view: LoginView,
        override val bus: Bus,
        val interactor: SignInWithGoogle,
        val interactorExecutor: FirebaseInteractorExecutor) : BasePresenter<LoginView> {

    @Subscribe
    fun onEvent(event: SignInSuccessEvent) {
        view.successfulLogin()
    }

    @Subscribe
    fun onEvent(event: SignInFailureEvent) {
        view.unsuccessfullogin(event.message)
    }

    fun signInWithGoogle(googleSignInAccount: GoogleSignInAccount) {
        val interactorDetail = interactor
        interactorDetail.account = googleSignInAccount
        interactorExecutor.execute(interactorDetail)
    }
}