package dk.mathiaspedersen.tripbook.presentation.presenter

import android.util.Log
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import dk.mathiaspedersen.tripbook.domain.interactor.ManagerInteractorImpl
import dk.mathiaspedersen.tripbook.domain.interactor.event.bus.Bus
import dk.mathiaspedersen.tripbook.domain.interactor.event.manager.SuccessfulLoginEvent
import dk.mathiaspedersen.tripbook.domain.interactor.event.manager.UnsuccessfulLoginEvent
import dk.mathiaspedersen.tripbook.domain.interactor.manager.ManagerInteractorExecutor
import dk.mathiaspedersen.tripbook.presentation.view.LoginView
import org.greenrobot.eventbus.Subscribe

class LoginPresenter(
        override val view: LoginView,
        override val bus: Bus,
        val managerInteractor: ManagerInteractorImpl,
        val managerInteractorExecutor: ManagerInteractorExecutor) : BasePresenter<LoginView> {

    @Subscribe
    fun onEvent(event: SuccessfulLoginEvent) {
        Log.d("TESTER", "BasePresenter is calling")
        view.successfulLogin()
    }

    @Subscribe
    fun onEvent(event: UnsuccessfulLoginEvent) {
        view.unsuccessfullogin(event.message)
    }

    fun signInWithGoogle(googleSignInAccount: GoogleSignInAccount) {
        managerInteractorExecutor.execute(managerInteractor, googleSignInAccount)
    }
}