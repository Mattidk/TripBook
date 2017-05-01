package dk.mathiaspedersen.tripbook.domain.interactor

import dk.mathiaspedersen.tripbook.domain.interactor.base.BaseUseCase
import dk.mathiaspedersen.tripbook.domain.interactor.base.Params
import dk.mathiaspedersen.tripbook.domain.manager.Manager
import io.reactivex.Observable

class SignOut(val manager: Manager): BaseUseCase() {

    override fun getObservable(params: Params): Observable<String> {
        return manager.signOut()
    }
}