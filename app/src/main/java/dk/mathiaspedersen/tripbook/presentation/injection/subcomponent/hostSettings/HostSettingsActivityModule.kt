package dk.mathiaspedersen.tripbook.presentation.injection.subcomponent.hostSettings

import dagger.Module
import dagger.Provides
import dk.mathiaspedersen.tripbook.domain.interactor.base.Bus
import dk.mathiaspedersen.tripbook.presentation.activity.HostSettingsActivity
import dk.mathiaspedersen.tripbook.presentation.fragment.SettingsFragment
import dk.mathiaspedersen.tripbook.presentation.injection.ActivityModule
import dk.mathiaspedersen.tripbook.presentation.injection.scope.ActivityScope
import dk.mathiaspedersen.tripbook.presentation.presenter.HostSettingsPresenter
import dk.mathiaspedersen.tripbook.presentation.view.HostSettingsView

@Module
class HostSettingsActivityModule(activity: HostSettingsActivity) : ActivityModule(activity) {

    @Provides @ActivityScope
    fun provideHostSettingsView(): HostSettingsView = activity as HostSettingsView


    @Provides @ActivityScope
    fun provideHostSettingsPresenter(view: HostSettingsView, bus: Bus)
            = HostSettingsPresenter(view, bus)

    @Provides @ActivityScope
    fun provideSettingsFragment() = SettingsFragment()

}