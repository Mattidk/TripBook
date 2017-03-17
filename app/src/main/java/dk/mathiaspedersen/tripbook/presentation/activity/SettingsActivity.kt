package dk.mathiaspedersen.tripbook.presentation.activity

import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import butterknife.BindView
import butterknife.ButterKnife
import dk.mathiaspedersen.tripbook.R
import dk.mathiaspedersen.tripbook.presentation.fragment.SettingsFragment
import dk.mathiaspedersen.tripbook.presentation.injection.ApplicationComponent
import dk.mathiaspedersen.tripbook.presentation.injection.subcomponent.hostSettings.HostSettingsActivityModule
import dk.mathiaspedersen.tripbook.presentation.presenter.HostSettingsPresenter
import dk.mathiaspedersen.tripbook.presentation.view.HostSettingsView
import org.jetbrains.anko.startActivity
import javax.inject.Inject

class SettingsActivity : BaseActivity(), HostSettingsView {

    override val layoutResource: Int = R.layout.activity_host_settings

    @BindView(R.id.toolbar)
    lateinit var toolbar: Toolbar

    @Inject
    lateinit var settings: SettingsFragment

    @Inject
    lateinit var presenter: HostSettingsPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ButterKnife.bind(this)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, settings)
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

    override fun injectDependencies(applicationComponent: ApplicationComponent) {
        applicationComponent.plus(HostSettingsActivityModule(this))
                .injectTo(this)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            android.R.id.home -> {
                //todo maybe there is a nother solution to this
                startActivity<HostActivity>()
                overridePendingTransition(R.anim.activity_slide_out_right, R.anim.activity_slide_in_left)
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        if (settings.isVisible) {
            //todo maybe there is a better way to do this.
            startActivity<HostActivity>()
            overridePendingTransition(R.anim.activity_slide_in_left, R.anim.activity_slide_out_right)
            finish()
        } else {
            super.onBackPressed()
        }
    }
}