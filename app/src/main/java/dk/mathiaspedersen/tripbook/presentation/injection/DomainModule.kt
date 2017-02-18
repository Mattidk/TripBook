package dk.mathiaspedersen.tripbook.presentation.injection

import dagger.Module
import dagger.Provides
import dk.mathiaspedersen.tripbook.domain.interactor.GetExampleFirebaseInteractor
import dk.mathiaspedersen.tripbook.domain.interactor.GetExampleInteractor
import dk.mathiaspedersen.tripbook.domain.interactor.event.bus.Bus
import dk.mathiaspedersen.tripbook.domain.repository.ExampleFirebaseRepository
import dk.mathiaspedersen.tripbook.domain.repository.ExampleRepository

@Module
class DomainModule {

    @Provides
    fun provideExampleInteractor(exampleRepository: ExampleRepository)
            = GetExampleInteractor(exampleRepository)

    @Provides
    fun provideExampleFirebaseInteractor(exampleFirebaseRepository: ExampleFirebaseRepository, bus: Bus)
            = GetExampleFirebaseInteractor(exampleFirebaseRepository, bus)
}
