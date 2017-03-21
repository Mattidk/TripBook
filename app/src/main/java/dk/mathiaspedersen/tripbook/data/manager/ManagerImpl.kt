package dk.mathiaspedersen.tripbook.data.manager

import android.os.Bundle
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.common.api.GoogleApiClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import dk.mathiaspedersen.tripbook.data.entity.UserEntity
import dk.mathiaspedersen.tripbook.data.entity.mapper.UserMapper
import dk.mathiaspedersen.tripbook.domain.interactor.GetUserProfile
import dk.mathiaspedersen.tripbook.domain.interactor.SignInWithGoogle
import dk.mathiaspedersen.tripbook.domain.interactor.SignOut
import dk.mathiaspedersen.tripbook.domain.manager.Manager


class ManagerImpl(val auth: FirebaseAuth, val userMapper: UserMapper, val client: GoogleApiClient) : Manager {

    override fun getUserProfile(callback: GetUserProfile) {
        val user = auth.currentUser
        if (user != null) {
            val name = user.displayName
            val photo = user.photoUrl
            val email = user.email
            callback.onSuccess(userMapper.transform(UserEntity(name, photo, email)))
        } else {
            callback.onFailure("User was null")
        }
    }

    override fun signInWithGoogle(callback: SignInWithGoogle, googleSignInAccount: GoogleSignInAccount?) {

        val credential = GoogleAuthProvider.getCredential(googleSignInAccount?.idToken, null)
        auth.signInWithCredential(credential).addOnCompleteListener {
            if (it.isSuccessful) {
                callback.onSuccess()
            } else {
                callback.onFailure(it.exception.toString())
            }
        }
    }

    override fun signOut(callback: SignOut) {

        client.registerConnectionCallbacks(object : GoogleApiClient.ConnectionCallbacks {
            override fun onConnected(p0: Bundle?) {
                Auth.GoogleSignInApi.signOut(client).setResultCallback {
                    client.unregisterConnectionCallbacks(this)
                    client.disconnect()
                    if (it.isSuccess) {
                        auth.signOut()
                        callback.onSuccess()
                    }
                }
            }

            override fun onConnectionSuspended(p0: Int) {
                callback.onFailure("")
            }
        })
        client.connect()
    }
}