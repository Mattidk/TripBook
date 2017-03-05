package dk.mathiaspedersen.tripbook.domain.manager

import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import dk.mathiaspedersen.tripbook.domain.interactor.SignInWithGoogle
import dk.mathiaspedersen.tripbook.domain.interactor.SignOut

interface Manager {
    fun signInWithGoogle(callback: SignInWithGoogle, googleSignInAccount: GoogleSignInAccount?)
    fun signOut(callback: SignOut)
}