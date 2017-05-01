package dk.mathiaspedersen.tripbook.domain.manager

import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import dk.mathiaspedersen.tripbook.domain.entity.User
import io.reactivex.Observable

interface Manager {
    fun signOut(): Observable<String>
    fun getUser(): Observable<User>
    fun googleSignIn(account: GoogleSignInAccount): Observable<GoogleSignInAccount>
}