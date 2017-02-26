package dk.mathiaspedersen.tripbook.presentation.injection

import android.content.Context
import com.path.android.jobqueue.JobManager
import dagger.Module
import dagger.Provides
import dk.mathiaspedersen.tripbook.App
import dk.mathiaspedersen.tripbook.data.executor.CustomJobManager
import dk.mathiaspedersen.tripbook.domain.interactor.event.bus.Bus
import dk.mathiaspedersen.tripbook.domain.interactor.event.bus.BusImpl
import dk.mathiaspedersen.tripbook.domain.interactor.manager.ManagerInteractorExecutor
import dk.mathiaspedersen.tripbook.domain.interactor.manager.ManagerInteractorExecutorImpl
import dk.mathiaspedersen.tripbook.domain.interactor.standard.JobInteractorExecutor
import dk.mathiaspedersen.tripbook.domain.interactor.standard.JobInteractorExecutorImpl
import dk.mathiaspedersen.tripbook.domain.interactor.trip.TripInteractorExecutor
import dk.mathiaspedersen.tripbook.domain.interactor.trip.TripInteractorExecutorImpl
import dk.mathiaspedersen.tripbook.presentation.injection.qualifier.ApplicationQualifier
import javax.inject.Singleton

@Module
class ApplicationModule(private val app: App) {

    @Provides @Singleton
    fun provideApplication(): App = app

    @Provides @Singleton @ApplicationQualifier
    fun provideApplicationContext(): Context = app

    @Provides @Singleton
    fun provideBus(): Bus = BusImpl()

    @Provides @Singleton
    fun provideJobManager(@ApplicationQualifier context: Context): JobManager = CustomJobManager(context)

    @Provides @Singleton
    fun provideJobInteractorExecutor(jobManager: JobManager, bus: Bus): JobInteractorExecutor
            = JobInteractorExecutorImpl(jobManager, bus)

    @Provides @Singleton
    fun provideInteractorExecutor(): TripInteractorExecutor
            = TripInteractorExecutorImpl()

    @Provides @Singleton
    fun provideAuthInteractorExecutor(): ManagerInteractorExecutor
            = ManagerInteractorExecutorImpl()

}