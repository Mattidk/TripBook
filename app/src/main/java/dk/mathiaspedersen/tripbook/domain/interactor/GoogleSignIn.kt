package dk.mathiaspedersen.tripbook.domain.interactor

import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import dk.mathiaspedersen.tripbook.domain.interactor.base.BaseUseCase
import dk.mathiaspedersen.tripbook.domain.interactor.base.Params
import dk.mathiaspedersen.tripbook.domain.manager.Manager
import io.reactivex.Observable

class GoogleSignIn(val manager: Manager) : BaseUseCase() {

    override fun getObservable(params: Params): Observable<GoogleSignInAccount> {
        return manager.googleSignIn(params.getGoogleSignInAccount("account", null))
    }
}