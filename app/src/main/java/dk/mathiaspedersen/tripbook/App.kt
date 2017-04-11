package dk.mathiaspedersen.tripbook

import android.app.Application
import android.preference.PreferenceManager
import android.support.v7.app.AppCompatDelegate
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
        initializeTheme()
        initializeDagger()
        initializeLeakDetection()
//        initializeDiskPersistance()
    }

    fun initializeDagger() {
        graph = DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this))
                .build()
    }

    fun initializeTheme() {
        val state = PreferenceManager.getDefaultSharedPreferences(this).getBoolean("pref_key_dark_theme", false)
        if (state) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }

    fun initializeLeakDetection() {
        if (LeakCanary.isInAnalyzerProcess(this)) return
        if (BuildConfig.DEBUG) LeakCanary.install(this)
    }

    private fun initializeDiskPersistance() {
        FirebaseDatabase.getInstance().setPersistenceEnabled(true)
    }
}