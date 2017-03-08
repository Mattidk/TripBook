package dk.mathiaspedersen.tripbook.presentation.presenter

import dk.mathiaspedersen.tripbook.domain.interactor.base.Bus
import dk.mathiaspedersen.tripbook.domain.interactor.event.GetProfileSuccessEvent
import dk.mathiaspedersen.tripbook.presentation.view.HostSettingsView
import org.greenrobot.eventbus.Subscribe

class HostSettingsPresenter(
        override val view: HostSettingsView,
        override val bus: Bus) : BasePresenter<HostSettingsView> {

    @Subscribe
    fun onEvent(event: GetProfileSuccessEvent) {
//        view.onGetProfileSuccess(userDetailDataMapper.transformUser(event.user))
    }

}