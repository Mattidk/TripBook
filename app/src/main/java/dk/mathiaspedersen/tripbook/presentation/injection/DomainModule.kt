package dk.mathiaspedersen.tripbook.presentation.injection

import dagger.Module
import dagger.Provides
import dk.mathiaspedersen.tripbook.domain.interactor.DrawPolyline
import dk.mathiaspedersen.tripbook.domain.interactor.GetUnclassifiedTrips
import dk.mathiaspedersen.tripbook.domain.interactor.SignInWithGoogle
import dk.mathiaspedersen.tripbook.domain.interactor.SignOut
import dk.mathiaspedersen.tripbook.domain.interactor.base.Bus
import dk.mathiaspedersen.tripbook.domain.manager.Manager
import dk.mathiaspedersen.tripbook.domain.repository.FirebaseRepository

@Module
class DomainModule {

    @Provides
    fun provideSignOut(manager: Manager, bus: Bus)
            = SignOut(manager, bus)

    @Provides
    fun provideSignInWithGoogle(manager: Manager, bus: Bus)
            = SignInWithGoogle(manager, bus)

    @Provides
    fun provideGetUnclassifiedTrips(repository: FirebaseRepository, bus: Bus)
            = GetUnclassifiedTrips(repository, bus)

    @Provides
    fun provideLoadDetailInteractorInteractor() = DrawPolyline()

}
