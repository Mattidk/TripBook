package dk.mathiaspedersen.tripbook.presentation.injection

import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import dagger.Module
import dagger.Provides
import dk.mathiaspedersen.tripbook.data.entity.mapper.TripMapper
import dk.mathiaspedersen.tripbook.data.repository.ExampleJobRepositoryImpl
import dk.mathiaspedersen.tripbook.data.repository.TripRepositoryImpl
import dk.mathiaspedersen.tripbook.domain.interactor.event.bus.Bus
import dk.mathiaspedersen.tripbook.domain.repository.ExampleJobRepository
import dk.mathiaspedersen.tripbook.domain.repository.TripRepository
import dk.mathiaspedersen.tripbook.presentation.injection.qualifier.ApplicationQualifier
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    fun provideTripMapper() = TripMapper()

    @Provides @Singleton
    fun provideExampleJobRepo() : ExampleJobRepository
            = ExampleJobRepositoryImpl()

    @Provides @Singleton
    fun provideExampleRepo(@ApplicationQualifier context: Context, database: FirebaseDatabase, auth: FirebaseAuth, tripMapper: TripMapper, bus: Bus) : TripRepository
            = TripRepositoryImpl(context, database, auth, tripMapper, bus)

}