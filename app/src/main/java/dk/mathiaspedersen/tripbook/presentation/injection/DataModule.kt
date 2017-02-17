package dk.mathiaspedersen.tripbook.presentation.injection

import android.content.Context
import com.google.android.gms.appinvite.AppInvite
import com.google.android.gms.common.api.GoogleApiClient
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import dagger.Module
import dagger.Provides

@Module
class DataModule {

    @Provides
    fun provideAnalytics(context: Context): FirebaseAnalytics = FirebaseAnalytics.getInstance(context)

    @Provides
    fun provideDatabase(): FirebaseDatabase = FirebaseDatabase.getInstance()

    @Provides
    fun provideAuthentication(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    fun provideRemoteConfig(): FirebaseRemoteConfig = FirebaseRemoteConfig.getInstance()

    @Provides
    fun provideInvites(context: Context): GoogleApiClient {
        return GoogleApiClient.Builder(context)
                .addApi(AppInvite.API)
                .build()
    }
}