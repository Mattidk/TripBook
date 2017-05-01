package dk.mathiaspedersen.tripbook.presentation.injection

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import dagger.Module
import dagger.Provides
import dk.mathiaspedersen.tripbook.App
import dk.mathiaspedersen.tripbook.presentation.helper.AppSettings
import dk.mathiaspedersen.tripbook.presentation.helper.AppSettingsImpl
import dk.mathiaspedersen.tripbook.presentation.injection.qualifier.AppQualifier
import dk.mathiaspedersen.tripbook.presentation.util.Utils
import javax.inject.Singleton


@Module
class ApplicationModule(private val app: App) {

    @Provides @Singleton
    fun provideApplication(): App = app

    @Provides @Singleton @AppQualifier
    fun provideApplicationContext(): Context = app

    @Provides @Singleton
    fun provideSharedPreferences(@AppQualifier context: Context): SharedPreferences
            = PreferenceManager.getDefaultSharedPreferences(context)

    @Provides @Singleton
    fun provideAppSettings(@AppQualifier context: Context, sharedPreferences: SharedPreferences): AppSettings
            = AppSettingsImpl(context, sharedPreferences)

    @Provides @Singleton
    fun provideUtils(@AppQualifier context: Context, settings: AppSettings): Utils = Utils(context, settings)

}