package dk.mathiaspedersen.tripbook.domain.interactor.manager

import com.google.android.gms.auth.api.signin.GoogleSignInAccount

interface ManagerInteractorExecutor {

    fun execute(interactor: ManagerInteractor, googleSignInAccount: GoogleSignInAccount)
    fun signOut(interactor: ManagerInteractor)
}