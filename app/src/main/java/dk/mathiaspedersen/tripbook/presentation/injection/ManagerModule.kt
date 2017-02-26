package dk.mathiaspedersen.tripbook.presentation.injection

import com.google.android.gms.common.api.GoogleApiClient
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dk.mathiaspedersen.tripbook.data.manager.ManagerImpl
import dk.mathiaspedersen.tripbook.domain.manager.Manager
import javax.inject.Singleton

@Module
class ManagerModule {

    @Provides @Singleton
    fun provideManager(auth: FirebaseAuth, client: GoogleApiClient) : Manager
            = ManagerImpl(auth, client)

}