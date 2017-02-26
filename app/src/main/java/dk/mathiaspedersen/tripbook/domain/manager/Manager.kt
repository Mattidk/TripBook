package dk.mathiaspedersen.tripbook.domain.manager

import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import dk.mathiaspedersen.tripbook.domain.interactor.manager.ManagerInteractor

interface Manager {
    fun signInGoogle(callback: ManagerInteractor, googleSignInAccount: GoogleSignInAccount)
    fun signInEmail()
    fun signUpEmail()
    fun signOut(callback: ManagerInteractor)
}