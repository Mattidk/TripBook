package dk.mathiaspedersen.tripbook.presentation.injection

import dagger.Module
import dagger.Provides
import dk.mathiaspedersen.tripbook.domain.repository.ExampleRepository
import dk.mathiaspedersen.tripbook.domain.repository.ExampleRepositoryImpl
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides @Singleton
    fun provideExampleRepo() : ExampleRepository
            = ExampleRepositoryImpl()

}