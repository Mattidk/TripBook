package dk.mathiaspedersen.tripbook.presentation.presenter

import dk.mathiaspedersen.tripbook.presentation.view.HostSettingsView

class HostSettingsPresenter(override val view: HostSettingsView) : BasePresenter<HostSettingsView> {

    override fun dispose() {
        // Used this to clean up observables
    }
}