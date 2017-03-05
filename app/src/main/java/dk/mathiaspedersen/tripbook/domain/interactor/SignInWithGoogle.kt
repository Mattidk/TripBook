package dk.mathiaspedersen.tripbook.domain.interactor

import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import dk.mathiaspedersen.tripbook.domain.interactor.base.Bus
import dk.mathiaspedersen.tripbook.domain.interactor.event.manager.SignInSuccessEvent
import dk.mathiaspedersen.tripbook.domain.interactor.event.manager.SignInFailureEvent
import dk.mathiaspedersen.tripbook.domain.interactor.base.firebase.FirebaseInteractor
import dk.mathiaspedersen.tripbook.domain.manager.Manager

class SignInWithGoogle(val manager: Manager, val bus: Bus) : FirebaseInteractor {

    var account: GoogleSignInAccount? = null

    override fun invoke() {
        manager.signInWithGoogle(this, account)
    }

    fun onSuccess() {
        bus.post(SignInSuccessEvent())
    }

    fun onFailure(message: String) {
        bus.post(SignInFailureEvent(message))
    }
}