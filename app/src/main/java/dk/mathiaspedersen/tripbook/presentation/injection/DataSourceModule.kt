package dk.mathiaspedersen.tripbook.presentation.injection

import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import dagger.Module
import dagger.Provides
import dk.mathiaspedersen.tripbook.data.repository.datasource.TripDataSource
import dk.mathiaspedersen.tripbook.data.repository.datasource.VehicleDataSource
import dk.mathiaspedersen.tripbook.presentation.injection.qualifier.AppQualifier
import dk.mathiaspedersen.tripbook.presentation.util.Utils
import javax.inject.Singleton

@Module
class DataSourceModule {

    @Provides @Singleton
    fun provideTripDataSource(@AppQualifier context: Context, database: FirebaseDatabase, auth: FirebaseAuth,
                              utils: Utils): TripDataSource = TripDataSource(context, database, auth, utils)

    @Provides @Singleton
    fun provideVehicleDataSource(@AppQualifier context: Context, database: FirebaseDatabase, auth: FirebaseAuth,
                              utils: Utils): VehicleDataSource = VehicleDataSource(context, database, auth, utils)

}