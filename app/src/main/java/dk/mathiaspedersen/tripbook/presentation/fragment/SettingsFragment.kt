package dk.mathiaspedersen.tripbook.presentation.fragment

import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceFragment
import android.util.Log
import dk.mathiaspedersen.tripbook.App
import dk.mathiaspedersen.tripbook.R
import dk.mathiaspedersen.tripbook.presentation.injection.ApplicationComponent
import dk.mathiaspedersen.tripbook.presentation.injection.subcomponent.settings.SettingsFragmentModule
import dk.mathiaspedersen.tripbook.presentation.presenter.SettingsPresenter
import dk.mathiaspedersen.tripbook.presentation.view.SettingsView
import javax.inject.Inject

class SettingsFragment: PreferenceFragment(), SettingsView, SharedPreferences.OnSharedPreferenceChangeListener {

    @Inject
    lateinit var presenter: SettingsPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addPreferencesFromResource(R.xml.preferences)
        injectDependencies(App.graph)
    }

    override fun onResume() {
        super.onResume()
        presenter.onResume()
        preferenceScreen.sharedPreferences.
                registerOnSharedPreferenceChangeListener(this)
    }

    override fun onPause() {
        super.onPause()
        presenter.onPause()
        preferenceScreen.sharedPreferences.
                unregisterOnSharedPreferenceChangeListener(this)
    }

    fun injectDependencies(applicationComponent: ApplicationComponent) {
        applicationComponent.plus(SettingsFragmentModule(this))
                .injectTo(this)
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
        when(key){
            "pref_key_dark_theme" -> Log.d("GODA", "DARK THEME WAS TOGGLED")
        }
    }
}