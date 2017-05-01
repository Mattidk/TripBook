package dk.mathiaspedersen.tripbook.domain.interactor

import dk.mathiaspedersen.tripbook.domain.entity.User
import dk.mathiaspedersen.tripbook.domain.interactor.base.BaseUseCase
import dk.mathiaspedersen.tripbook.domain.interactor.base.Params
import dk.mathiaspedersen.tripbook.domain.manager.Manager
import io.reactivex.Observable

class GetUser(val manager: Manager): BaseUseCase() {

    override fun getObservable(params: Params): Observable<User> {
        return manager.getUser()
    }
}