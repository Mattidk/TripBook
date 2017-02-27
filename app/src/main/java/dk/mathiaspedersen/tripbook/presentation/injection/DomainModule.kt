package dk.mathiaspedersen.tripbook.presentation.injection

import dagger.Module
import dagger.Provides
import dk.mathiaspedersen.tripbook.domain.interactor.ExampleInteractorImpl
import dk.mathiaspedersen.tripbook.domain.interactor.ExampleJobInteractorImpl
import dk.mathiaspedersen.tripbook.domain.interactor.ManagerInteractorImpl
import dk.mathiaspedersen.tripbook.domain.interactor.event.bus.Bus
import dk.mathiaspedersen.tripbook.domain.manager.Manager
import dk.mathiaspedersen.tripbook.domain.repository.ExampleJobRepository
import dk.mathiaspedersen.tripbook.domain.repository.TripRepository

@Module
class DomainModule {

    @Provides
    fun provideManagerInteractor(manager: Manager, bus: Bus)
            = ManagerInteractorImpl(manager, bus)

    @Provides
    fun provideExampleJobInteractor(repository: ExampleJobRepository)
            = ExampleJobInteractorImpl(repository)

    @Provides
    fun provideExampleInteractor(repository: TripRepository, bus: Bus)
            = ExampleInteractorImpl(repository, bus)

}
