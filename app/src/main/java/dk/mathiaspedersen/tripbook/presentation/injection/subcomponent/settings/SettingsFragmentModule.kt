package dk.mathiaspedersen.tripbook.presentation.injection.subcomponent.settings

import dagger.Module
import dagger.Provides
import dk.mathiaspedersen.tripbook.domain.interactor.SignOut
import dk.mathiaspedersen.tripbook.domain.interactor.base.Bus
import dk.mathiaspedersen.tripbook.domain.interactor.base.firebase.FirebaseInteractorExecutor
import dk.mathiaspedersen.tripbook.presentation.fragment.SettingsFragment
import dk.mathiaspedersen.tripbook.presentation.injection.FragmentModule
import dk.mathiaspedersen.tripbook.presentation.presenter.SettingsPresenter
import dk.mathiaspedersen.tripbook.presentation.view.SettingsView

@Module
class SettingsFragmentModule(fragment: SettingsFragment) : FragmentModule(fragment) {

    @Provides
    fun provideSettingsView(): SettingsView = fragment as SettingsView

    @Provides
    fun provideSettingsPresenter(view: SettingsView, bus: Bus, interactor: SignOut, interactorExecutor:
                                FirebaseInteractorExecutor) = SettingsPresenter(view, bus, interactor,
                                interactorExecutor)

}