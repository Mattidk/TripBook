package dk.mathiaspedersen.tripbook.presentation.injection

import dagger.Component
import dk.mathiaspedersen.tripbook.presentation.injection.subcomponent.detail.DetailActivityComponent
import dk.mathiaspedersen.tripbook.presentation.injection.subcomponent.detail.DetailActivityModule
import dk.mathiaspedersen.tripbook.presentation.injection.subcomponent.history.HistoryFragmentComponent
import dk.mathiaspedersen.tripbook.presentation.injection.subcomponent.history.HistoryFragmentModule
import dk.mathiaspedersen.tripbook.presentation.injection.subcomponent.host.HostActivityComponent
import dk.mathiaspedersen.tripbook.presentation.injection.subcomponent.host.HostActivityModule
import dk.mathiaspedersen.tripbook.presentation.injection.subcomponent.hostSettings.HostSettingsActivityComponent
import dk.mathiaspedersen.tripbook.presentation.injection.subcomponent.hostSettings.HostSettingsActivityModule
import dk.mathiaspedersen.tripbook.presentation.injection.subcomponent.login.LoginActivityComponent
import dk.mathiaspedersen.tripbook.presentation.injection.subcomponent.login.LoginActivityModule
import dk.mathiaspedersen.tripbook.presentation.injection.subcomponent.settings.SettingsFragmentComponent
import dk.mathiaspedersen.tripbook.presentation.injection.subcomponent.settings.SettingsFragmentModule
import dk.mathiaspedersen.tripbook.presentation.injection.subcomponent.trips.TripsFragmentComponent
import dk.mathiaspedersen.tripbook.presentation.injection.subcomponent.trips.TripsFragmentModule
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(
        ApplicationModule::class,
        RepositoryModule::class,
        DomainModule::class,
        DataModule::class,
        ManagerModule::class
))
interface ApplicationComponent {

    fun plus(module: LoginActivityModule): LoginActivityComponent
    fun plus(module: HostActivityModule): HostActivityComponent
    fun plus(module: TripsFragmentModule): TripsFragmentComponent
    fun plus(module: HistoryFragmentModule): HistoryFragmentComponent
    fun plus(module: DetailActivityModule): DetailActivityComponent
    fun plus(module: HostSettingsActivityModule): HostSettingsActivityComponent
    fun plus(module: SettingsFragmentModule): SettingsFragmentComponent
}