package dk.mathiaspedersen.tripbook.presentation.presenter

import dk.mathiaspedersen.tripbook.domain.interactor.SignOut
import dk.mathiaspedersen.tripbook.domain.interactor.base.DefaultObserver
import dk.mathiaspedersen.tripbook.domain.interactor.base.Params
import dk.mathiaspedersen.tripbook.presentation.view.SettingsView

class SettingsPresenter(override val view: SettingsView, val signOut: SignOut) : BasePresenter<SettingsView> {

    fun signOut() {
        signOut.execute(SignOutObserver(), Params.EMPTY)
    }

    override fun dispose() {
        signOut.dispose()
    }

    inner class SignOutObserver: DefaultObserver<String>() {
        override fun onNext(t: String) {
            super.onNext(t)
            view.signOutSuccessful()
        }

        override fun onComplete() {
            super.onComplete()
        }

        override fun onError(exception: Throwable) {
            super.onError(exception)
            view.signOutUnsuccessful()
        }
    }
}