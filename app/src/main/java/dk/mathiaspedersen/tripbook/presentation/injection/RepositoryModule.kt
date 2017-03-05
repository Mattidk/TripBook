package dk.mathiaspedersen.tripbook.presentation.injection

import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import dagger.Module
import dagger.Provides
import dk.mathiaspedersen.tripbook.data.entity.mapper.TripMapper
import dk.mathiaspedersen.tripbook.data.repository.FirebaseRepositoryImpl
import dk.mathiaspedersen.tripbook.domain.interactor.base.Bus
import dk.mathiaspedersen.tripbook.domain.repository.FirebaseRepository
import dk.mathiaspedersen.tripbook.presentation.injection.qualifier.AppQualifier
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    fun provideTripMapper() = TripMapper()

    @Provides @Singleton
    fun provideFirebaseRepo(@AppQualifier context: Context, database: FirebaseDatabase,
                            auth: FirebaseAuth, tripMapper: TripMapper, bus: Bus) :
            FirebaseRepository = FirebaseRepositoryImpl(context, database, auth, tripMapper, bus)

}