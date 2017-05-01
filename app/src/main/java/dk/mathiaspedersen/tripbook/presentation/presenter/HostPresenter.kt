package dk.mathiaspedersen.tripbook.presentation.presenter

import dk.mathiaspedersen.tripbook.domain.entity.User
import dk.mathiaspedersen.tripbook.domain.interactor.GetUser
import dk.mathiaspedersen.tripbook.domain.interactor.base.DefaultObserver
import dk.mathiaspedersen.tripbook.domain.interactor.base.Params
import dk.mathiaspedersen.tripbook.presentation.entity.mapper.UserDetailDataMapper
import dk.mathiaspedersen.tripbook.presentation.view.HostView

class HostPresenter(override val view: HostView, val getUser: GetUser, val mapper: UserDetailDataMapper) : BasePresenter<HostView> {

    fun getUserProfile() {
        getUser.execute(GetUserObserver(), Params.EMPTY)
    }

    override fun dispose() {
        getUser.dispose()
    }

    inner class GetUserObserver : DefaultObserver<User>() {
        override fun onNext(t: User) {
            super.onNext(t)
            view.onGetProfileSuccess(mapper.transformUser(t))
        }

        override fun onComplete() {
            super.onComplete()
        }

        override fun onError(exception: Throwable) {
            super.onError(exception)
            view.onGetProfileFailure("Failed to get user details")
        }
    }

}