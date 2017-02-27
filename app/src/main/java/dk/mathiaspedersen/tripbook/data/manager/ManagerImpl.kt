package dk.mathiaspedersen.tripbook.data.manager

import android.os.Bundle
import android.util.Log
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.common.api.GoogleApiClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import dk.mathiaspedersen.tripbook.domain.interactor.manager.ManagerInteractor
import dk.mathiaspedersen.tripbook.domain.manager.Manager
import dk.mathiaspedersen.tripbook.presentation.activity.LoginActivity
import org.jetbrains.anko.clearTop
import org.jetbrains.anko.intentFor


class ManagerImpl(val auth: FirebaseAuth, val client: GoogleApiClient) : Manager {

    override fun signInGoogle(callback: ManagerInteractor, googleSignInAccount: GoogleSignInAccount) {

        val credential = GoogleAuthProvider.getCredential(googleSignInAccount.idToken, null)
        auth.signInWithCredential(credential).addOnCompleteListener {
            if (it.isSuccessful) {
                callback.googleSignInSuccessful()
            } else {
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

        client.registerConnectionCallbacks(object : GoogleApiClient.ConnectionCallbacks {
            override fun onConnected(p0: Bundle?) {
                Auth.GoogleSignInApi.signOut(client).setResultCallback {
                    client.unregisterConnectionCallbacks(this)
                    client.disconnect()
                    if (it.isSuccess) {
                        auth.signOut()
                        callback.signOutSuccessful()
                    }
                }
            }
            override fun onConnectionSuspended(p0: Int) {
                callback.signOutUnsuccessful("")
            }
        })
        client.connect()
    }
}