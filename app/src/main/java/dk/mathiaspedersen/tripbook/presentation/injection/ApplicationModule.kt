package dk.mathiaspedersen.tripbook.presentation.injection

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.path.android.jobqueue.JobManager
import dagger.Module
import dagger.Provides
import dk.mathiaspedersen.tripbook.App
import dk.mathiaspedersen.tripbook.domain.interactor.base.Bus
import dk.mathiaspedersen.tripbook.domain.interactor.base.BusImpl
import dk.mathiaspedersen.tripbook.domain.interactor.base.CustomJobManager
import dk.mathiaspedersen.tripbook.domain.interactor.base.firebase.FirebaseInteractorExecutor
import dk.mathiaspedersen.tripbook.domain.interactor.base.firebase.FirebaseInteractorExecutorImpl
import dk.mathiaspedersen.tripbook.domain.interactor.base.standard.InteractorExecutor
import dk.mathiaspedersen.tripbook.domain.interactor.base.standard.InteractorExecutorImpl
import dk.mathiaspedersen.tripbook.presentation.helper.*
import dk.mathiaspedersen.tripbook.presentation.injection.qualifier.AppQualifier
import dk.mathiaspedersen.tripbook.presentation.injection.scope.ActivityScope
import javax.inject.Singleton


@Module
class ApplicationModule(private val app: App) {

    @Provides @Singleton
    fun provideApplication(): App = app

    @Provides @Singleton @AppQualifier
    fun provideApplicationContext(): Context = app

    @Provides @Singleton
    fun provideBus(): Bus = BusImpl()

    @Provides @Singleton
    fun provideJobManager(@AppQualifier context: Context): JobManager = CustomJobManager(context)

    @Provides @Singleton
    fun provideSharedPreferences(@AppQualifier context: Context): SharedPreferences
            = PreferenceManager.getDefaultSharedPreferences(context)

    @Provides @Singleton
    fun provideAppSettings(@AppQualifier context: Context, sharedPreferences: SharedPreferences): AppSettings
            = AppSettingsImpl(context, sharedPreferences)

    @Provides @Singleton
    fun provideInteractorExecutor(jobManager: JobManager, bus: Bus): InteractorExecutor
            = InteractorExecutorImpl(jobManager, bus)

    @Provides @Singleton
    fun provideFirebaseInteractorExecutor(jobManager: JobManager, bus: Bus): FirebaseInteractorExecutor
            = FirebaseInteractorExecutorImpl(jobManager, bus)

}