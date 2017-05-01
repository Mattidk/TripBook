package dk.mathiaspedersen.tripbook.presentation.injection

import dagger.Module
import dagger.Provides
import dk.mathiaspedersen.tripbook.domain.interactor.*
import dk.mathiaspedersen.tripbook.domain.manager.Manager
import dk.mathiaspedersen.tripbook.domain.repository.TripRepository
import dk.mathiaspedersen.tripbook.domain.repository.VehicleRepository

@Module
class DomainModule {

    @Provides
    fun provideGetTrips(repository: TripRepository): GetTrips = GetTrips(repository)

    @Provides
    fun provideGetTrip(repository: TripRepository): GetTrip = GetTrip(repository)

    @Provides
    fun provideGetVehicle(repository: VehicleRepository): GetVehicle = GetVehicle(repository)

    @Provides
    fun provideGoogleSignIn(manager: Manager): GoogleSignIn = GoogleSignIn(manager)

    @Provides
    fun provideCalculateBounds(): CalculateBounds = CalculateBounds()

    @Provides
    fun provideGetUser(manager: Manager): GetUser = GetUser(manager)

    @Provides
    fun provideSignOut(manager: Manager): SignOut = SignOut(manager)

}
