package dk.mathiaspedersen.tripbook

import android.app.Application
import com.google.firebase.database.FirebaseDatabase
import com.squareup.leakcanary.LeakCanary
import dk.mathiaspedersen.tripbook.presentation.injection.ApplicationComponent
import dk.mathiaspedersen.tripbook.presentation.injection.ApplicationModule
import dk.mathiaspedersen.tripbook.presentation.injection.DaggerApplicationComponent

class App : Application() {

    companion object {
        lateinit var graph: ApplicationComponent
    }

    override fun onCreate() {
        super.onCreate()
        initializeDagger()
        initializeLeakDetection()
        enableDiskPersistence()
    }

    fun initializeDagger() {
        graph = DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this))
                .build()
    }

    fun initializeLeakDetection() {
        if (LeakCanary.isInAnalyzerProcess(this)) return
        if (BuildConfig.DEBUG) LeakCanary.install(this)
    }

    fun enableDiskPersistence() {
        FirebaseDatabase.getInstance().setPersistenceEnabled(true)
    }
}