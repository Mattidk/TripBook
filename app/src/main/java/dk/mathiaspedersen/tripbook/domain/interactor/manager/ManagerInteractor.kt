package dk.mathiaspedersen.tripbook.domain.interactor.manager

import com.google.android.gms.auth.api.signin.GoogleSignInAccount

interface ManagerInteractor {
    operator fun invoke(googleSignInAccount: GoogleSignInAccount)
    fun googleSignInSuccessful()
    fun googleSignInUnsuccessful(message: String)
    fun emailSignInSuccessful()
    fun emailSignInUnsuccessful(message: String)
    fun emailSignUpSuccessful()
    fun emailSignUpUnsuccessful(message: String)
    fun signOutSuccessful()
    fun signOutUnsuccessful(message: String)
}