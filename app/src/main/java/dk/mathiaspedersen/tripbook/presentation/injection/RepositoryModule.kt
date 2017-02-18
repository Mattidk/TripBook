package dk.mathiaspedersen.tripbook.presentation.injection

import com.google.firebase.database.FirebaseDatabase
import dagger.Module
import dagger.Provides
import dk.mathiaspedersen.tripbook.data.repository.ExampleFirebaseRepositoryImpl
import dk.mathiaspedersen.tripbook.data.repository.ExampleRepositoryImpl
import dk.mathiaspedersen.tripbook.domain.interactor.event.bus.Bus
import dk.mathiaspedersen.tripbook.domain.repository.ExampleFirebaseRepository
import dk.mathiaspedersen.tripbook.domain.repository.ExampleRepository
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides @Singleton
    fun provideExampleRepo() : ExampleRepository
            = ExampleRepositoryImpl()

    @Provides @Singleton
    fun provideExampleFirebaseRepo(database: FirebaseDatabase, bus: Bus) : ExampleFirebaseRepository
            = ExampleFirebaseRepositoryImpl(database, bus)
}