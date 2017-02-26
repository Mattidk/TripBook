package dk.mathiaspedersen.tripbook.data.manager

import android.util.Log
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.common.api.GoogleApiClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import dk.mathiaspedersen.tripbook.domain.interactor.manager.ManagerInteractor
import dk.mathiaspedersen.tripbook.domain.manager.Manager


class ManagerImpl(val auth: FirebaseAuth, val client: GoogleApiClient) : Manager {

    override fun signInGoogle(callback: ManagerInteractor, googleSignInAccount: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(googleSignInAccount.idToken, null)
        auth.signInWithCredential(credential).addOnCompleteListener {
            if (it.isSuccessful) {
                Log.d("TESTER", "Successful Login")
                callback.googleSignInSuccessful()
            } else {
                Log.d("TESTER", it.exception.toString())
                callback.googleSignInUnsuccessful(it.exception.toString())
            }
        }
    }

    override fun signInEmail() {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun signUpEmail() {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun signOut(callback: ManagerInteractor) {
        auth.signOut()
        callback.signOutSuccessful()
    }
}