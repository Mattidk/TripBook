package dk.mathiaspedersen.tripbook.presentation.injection

import dagger.Component
import dk.mathiaspedersen.tripbook.presentation.injection.subcomponent.history.HistoryFragmentComponent
import dk.mathiaspedersen.tripbook.presentation.injection.subcomponent.history.HistoryFragmentModule
import dk.mathiaspedersen.tripbook.presentation.injection.subcomponent.container.ContainerActivityComponent
import dk.mathiaspedersen.tripbook.presentation.injection.subcomponent.container.ContainerActivityModule
import dk.mathiaspedersen.tripbook.presentation.injection.subcomponent.trips.TripsFragmentComponent
import dk.mathiaspedersen.tripbook.presentation.injection.subcomponent.trips.TripsFragmentModule
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(
        ApplicationModule::class,
        RepositoryModule::class,
        DomainModule::class,
        DataModule::class
))
interface ApplicationComponent {
    fun plus(module: ContainerActivityModule): ContainerActivityComponent
    fun plus(module: TripsFragmentModule): TripsFragmentComponent
    fun plus(module: HistoryFragmentModule): HistoryFragmentComponent
}