package dk.mathiaspedersen.tripbook.presentation.presenter

import dk.mathiaspedersen.tripbook.domain.interactor.base.Bus
import dk.mathiaspedersen.tripbook.domain.interactor.event.GetProfileSuccessEvent
import dk.mathiaspedersen.tripbook.presentation.view.HelpView
import org.greenrobot.eventbus.Subscribe

class HelpPresenter(
        override val view: HelpView,
        override val bus: Bus) : BasePresenter<HelpView> {

    @Subscribe
    fun onEvent(event: GetProfileSuccessEvent) {
//        view.onGetProfileSuccess(userDetailDataMapper.transformUser(event.user))
    }

}