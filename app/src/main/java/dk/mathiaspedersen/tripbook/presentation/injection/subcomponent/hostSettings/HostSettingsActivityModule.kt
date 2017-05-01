package dk.mathiaspedersen.tripbook.presentation.injection.subcomponent.hostSettings

import dagger.Module
import dagger.Provides
import dk.mathiaspedersen.tripbook.presentation.activity.SettingsActivity
import dk.mathiaspedersen.tripbook.presentation.fragment.SettingsFragment
import dk.mathiaspedersen.tripbook.presentation.injection.ActivityModule
import dk.mathiaspedersen.tripbook.presentation.injection.scope.ActivityScope
import dk.mathiaspedersen.tripbook.presentation.presenter.HostSettingsPresenter
import dk.mathiaspedersen.tripbook.presentation.view.HostSettingsView

@Module
class HostSettingsActivityModule(activity: SettingsActivity) : ActivityModule(activity) {

    @Provides @ActivityScope
    fun provideHostSettingsView(): HostSettingsView = activity as HostSettingsView


    @Provides @ActivityScope
    fun provideHostSettingsPresenter(view: HostSettingsView) = HostSettingsPresenter(view)

    @Provides @ActivityScope
    fun provideSettingsFragment() = SettingsFragment()

}