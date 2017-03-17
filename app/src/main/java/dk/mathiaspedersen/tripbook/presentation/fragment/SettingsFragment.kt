package dk.mathiaspedersen.tripbook.presentation.fragment

import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceFragment
import com.afollestad.materialdialogs.MaterialDialog
import dk.mathiaspedersen.tripbook.App
import dk.mathiaspedersen.tripbook.R
import dk.mathiaspedersen.tripbook.presentation.activity.LoginActivity
import dk.mathiaspedersen.tripbook.presentation.activity.SettingsActivity
import dk.mathiaspedersen.tripbook.presentation.injection.ApplicationComponent
import dk.mathiaspedersen.tripbook.presentation.injection.subcomponent.settings.SettingsFragmentModule
import dk.mathiaspedersen.tripbook.presentation.presenter.SettingsPresenter
import dk.mathiaspedersen.tripbook.presentation.view.SettingsView
import org.jetbrains.anko.clearTop
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.startActivity
import javax.inject.Inject

class SettingsFragment : PreferenceFragment(), SettingsView, SharedPreferences.OnSharedPreferenceChangeListener {

    @Inject
    lateinit var presenter: SettingsPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addPreferencesFromResource(R.xml.preferences)
        injectDependencies(App.graph)

        findPreference("pref_key_log_out").setOnPreferenceClickListener({
            MaterialDialog.Builder(activity)
                    .title("Are you sure?")
                    .content("You will be logged out!")
                    .positiveText("LOG OUT")
                    .negativeText("CLOSE")
                    .onPositive({ dialog, which ->
                        presenter.signOut()
                    }).onNegative { dialog, which -> }.show()
            true
        })
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

    override fun signOutSuccessful() {
        startActivity(intentFor<LoginActivity>().clearTop())
        activity.finish()
    }

    override fun signOutUnsuccessful() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun injectDependencies(applicationComponent: ApplicationComponent) {
        applicationComponent.plus(SettingsFragmentModule(this))
                .injectTo(this)
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
        when (key) {
            "pref_key_dark_theme" -> {
                startActivity<SettingsActivity>()
                activity.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
                activity.finish()
            }
        }
    }


}