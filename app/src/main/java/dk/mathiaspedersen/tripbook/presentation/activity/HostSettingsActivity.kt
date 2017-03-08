package dk.mathiaspedersen.tripbook.presentation.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import dk.mathiaspedersen.tripbook.App
import dk.mathiaspedersen.tripbook.presentation.fragment.SettingsFragment
import dk.mathiaspedersen.tripbook.presentation.injection.ApplicationComponent
import dk.mathiaspedersen.tripbook.presentation.injection.subcomponent.hostSettings.HostSettingsActivityModule
import dk.mathiaspedersen.tripbook.presentation.presenter.HostSettingsPresenter
import dk.mathiaspedersen.tripbook.presentation.view.HostSettingsView
import javax.inject.Inject

class HostSettingsActivity : AppCompatActivity(), HostSettingsView {

    @Inject
    lateinit var settings: SettingsFragment

    @Inject
    lateinit var presenter: HostSettingsPresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectDependencies(App.graph)

        fragmentManager.beginTransaction()
                .replace(android.R.id.content, settings)
                .commit()
    }

    override fun onResume() {
        super.onResume()
        presenter.onResume()
    }

    override fun onPause() {
        super.onPause()
        presenter.onPause()
    }

    fun injectDependencies(applicationComponent: ApplicationComponent) {
        applicationComponent.plus(HostSettingsActivityModule(this))
                .injectTo(this)
    }

}