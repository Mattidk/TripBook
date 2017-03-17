package dk.mathiaspedersen.tripbook.presentation.presenter

import dk.mathiaspedersen.tripbook.domain.interactor.GetUserProfile
import dk.mathiaspedersen.tripbook.domain.interactor.base.Bus
import dk.mathiaspedersen.tripbook.domain.interactor.base.firebase.FirebaseInteractorExecutor
import dk.mathiaspedersen.tripbook.domain.interactor.event.GetProfileFailureEvent
import dk.mathiaspedersen.tripbook.domain.interactor.event.GetProfileSuccessEvent
import dk.mathiaspedersen.tripbook.presentation.entity.mapper.UserDetailDataMapper
import dk.mathiaspedersen.tripbook.presentation.view.HostView
import org.greenrobot.eventbus.Subscribe

class HostPresenter(
        override val view: HostView,
        override val bus: Bus,
        val getUserInteractor: GetUserProfile,
        val interactorExecutor: FirebaseInteractorExecutor,
        val userDetailDataMapper: UserDetailDataMapper) : BasePresenter<HostView> {

    @Subscribe
    fun onEvent(event: GetProfileSuccessEvent) {
        view.onGetProfileSuccess(userDetailDataMapper.transformUser(event.user))
    }

    @Subscribe
    fun onEvent(event: GetProfileFailureEvent) {
        view.onGetProfileFailure(event.message)
    }

    fun getUserProfile() {
        interactorExecutor.execute(getUserInteractor)
    }

}