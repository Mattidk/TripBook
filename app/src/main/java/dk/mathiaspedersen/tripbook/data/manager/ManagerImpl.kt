package dk.mathiaspedersen.tripbook.data.manager

import android.os.Bundle
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.common.api.GoogleApiClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import dk.mathiaspedersen.tripbook.data.entity.UserEntity
import dk.mathiaspedersen.tripbook.data.entity.mapper.UserMapper
import dk.mathiaspedersen.tripbook.data.exceptions.AuthenticationException
import dk.mathiaspedersen.tripbook.data.exceptions.InvalidUserException
import dk.mathiaspedersen.tripbook.domain.entity.User
import dk.mathiaspedersen.tripbook.domain.manager.Manager
import io.reactivex.Observable


class ManagerImpl(val auth: FirebaseAuth, val userMapper: UserMapper, val client: GoogleApiClient) : Manager {

    override fun getUser(): Observable<User> {
        return Observable.create { emitter ->
            val user = auth.currentUser
            if (user != null) {
                val name = user.displayName
                val photo = user.photoUrl
                val email = user.email
                emitter.onNext(userMapper.transform(UserEntity(name, photo, email)))
            } else {
                emitter.onError(InvalidUserException("Error getting user details"))
            }
        }
    }

    override fun googleSignIn(account: GoogleSignInAccount): Observable<GoogleSignInAccount> {
        return Observable.create { emitter ->
            val credential = GoogleAuthProvider.getCredential(account.idToken, null)
            auth.signInWithCredential(credential).addOnCompleteListener {
                if (it.isSuccessful) {
                    emitter.onNext(account)
                } else {
                    emitter.onError(AuthenticationException("Could not sign in with Google"))
                }
            }
        }
    }

    override fun signOut(): Observable<String> {
        return Observable.create { emitter ->
            client.registerConnectionCallbacks(object : GoogleApiClient.ConnectionCallbacks {

                override fun onConnected(p0: Bundle?) {
                    Auth.GoogleSignInApi.signOut(client).setResultCallback {
                        client.unregisterConnectionCallbacks(this)
                        client.disconnect()
                        if (it.isSuccess) {
                            auth.signOut()
                            emitter.onNext("Success")
                        }else {
                            emitter.onError(AuthenticationException("Unable to sign user out"))
                        }
                    }
                }

                override fun onConnectionSuspended(p0: Int) {
                    emitter.onError(AuthenticationException("Unable to sign user out"))
                }
            })
            client.connect()
        }
    }
}