package dk.mathiaspedersen.tripbook.presentation.injection

import android.content.Context
import com.path.android.jobqueue.JobManager
import dagger.Module
import dagger.Provides
import dk.mathiaspedersen.tripbook.App
import dk.mathiaspedersen.tripbook.domain.BusImpl
import dk.mathiaspedersen.tripbook.domain.interactor.base.Bus
import dk.mathiaspedersen.tripbook.domain.interactor.base.CustomJobManager
import dk.mathiaspedersen.tripbook.domain.interactor.base.InteractorExecutor
import dk.mathiaspedersen.tripbook.domain.interactor.base.InteractorExecutorImpl
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
    fun provideInteractorExecutor(jobManager: JobManager, bus: Bus): InteractorExecutor = InteractorExecutorImpl(jobManager, bus)

}