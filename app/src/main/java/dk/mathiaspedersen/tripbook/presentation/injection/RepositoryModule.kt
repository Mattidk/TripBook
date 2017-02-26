package dk.mathiaspedersen.tripbook.presentation.injection

import com.google.firebase.database.FirebaseDatabase
import dagger.Module
import dagger.Provides
import dk.mathiaspedersen.tripbook.data.repository.ExampleJobRepositoryImpl
import dk.mathiaspedersen.tripbook.data.repository.TripRepositoryImpl
import dk.mathiaspedersen.tripbook.domain.interactor.event.bus.Bus
import dk.mathiaspedersen.tripbook.domain.repository.ExampleJobRepository
import dk.mathiaspedersen.tripbook.domain.repository.TripRepository
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides @Singleton
    fun provideExampleJobRepo() : ExampleJobRepository
            = ExampleJobRepositoryImpl()

    @Provides @Singleton
    fun provideExampleRepo(database: FirebaseDatabase, bus: Bus) : TripRepository
            = TripRepositoryImpl(database, bus)
}