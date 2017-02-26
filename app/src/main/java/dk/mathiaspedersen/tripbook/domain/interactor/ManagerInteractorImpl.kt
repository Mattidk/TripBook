package dk.mathiaspedersen.tripbook.domain.interactor

import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import dk.mathiaspedersen.tripbook.domain.interactor.event.bus.Bus
import dk.mathiaspedersen.tripbook.domain.interactor.event.manager.SuccessfulLoginEvent
import dk.mathiaspedersen.tripbook.domain.interactor.event.manager.UnsuccessfulLoginEvent
import dk.mathiaspedersen.tripbook.domain.interactor.manager.ManagerInteractor
import dk.mathiaspedersen.tripbook.domain.manager.Manager

class ManagerInteractorImpl(val manager: Manager, val bus: Bus) : ManagerInteractor {

    override fun googleSignInSuccessful() {
        bus.post(SuccessfulLoginEvent())
    }

    override fun googleSignInUnsuccessful(message: String) {
        bus.post(UnsuccessfulLoginEvent(message))
    }

    override fun emailSignInSuccessful() {
        throw UnsupportedOperationException("not implemented")
    }

    override fun emailSignInUnsuccessful(message: String) {
        throw UnsupportedOperationException("not implemented")
    }

    override fun emailSignUpSuccessful() {
        throw UnsupportedOperationException("not implemented")
    }

    override fun emailSignUpUnsuccessful(message: String) {
        throw UnsupportedOperationException("not implemented")
    }

    override fun signOutSuccessful() {
        throw UnsupportedOperationException("not implemented")
    }

    override fun signOutUnsuccessful(message: String) {
        throw UnsupportedOperationException("not implemented")
    }

    override fun invoke(googleSignInAccount: GoogleSignInAccount) {
        manager.signInGoogle(this, googleSignInAccount)
    }
}