package dk.mathiaspedersen.tripbook.domain.interactor.manager

import com.google.android.gms.auth.api.signin.GoogleSignInAccount

class ManagerInteractorExecutorImpl : ManagerInteractorExecutor {

    override fun execute(interactor: ManagerInteractor, googleSignInAccount: GoogleSignInAccount) {
        interactor.invoke(googleSignInAccount)
    }
}