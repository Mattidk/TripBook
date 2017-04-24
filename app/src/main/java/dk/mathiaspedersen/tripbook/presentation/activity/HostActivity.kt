package dk.mathiaspedersen.tripbook.presentation.activity

import android.app.Fragment
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.NavigationView
import android.support.design.widget.Snackbar
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.bumptech.glide.Glide
import dk.mathiaspedersen.tripbook.R
import dk.mathiaspedersen.tripbook.presentation.entity.UserDetail
import dk.mathiaspedersen.tripbook.presentation.fragment.RecentFragment
import dk.mathiaspedersen.tripbook.presentation.fragment.TripsFragment
import dk.mathiaspedersen.tripbook.presentation.helper.AppSettings
import dk.mathiaspedersen.tripbook.presentation.injection.ApplicationComponent
import dk.mathiaspedersen.tripbook.presentation.injection.subcomponent.host.HostActivityModule
import dk.mathiaspedersen.tripbook.presentation.presenter.HostPresenter
import dk.mathiaspedersen.tripbook.presentation.view.HostView
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.startActivityForResult
import javax.inject.Inject


class HostActivity : BaseActivity(), HostView, NavigationView.OnNavigationItemSelectedListener {

    override val layoutResource: Int = R.layout.activity_container

    @BindView(R.id.drawer_layout)
    lateinit var drawer: DrawerLayout

    @BindView(R.id.nav_view)
    lateinit var navigationView: NavigationView

    @BindView(R.id.fab)
    lateinit var fab: FloatingActionButton

    @BindView(R.id.toolbar)
    lateinit var toolbar: Toolbar

    @Inject
    lateinit var trips: TripsFragment

    @Inject
    lateinit var recent: RecentFragment

    @Inject
    lateinit var presenter: HostPresenter

    @Inject
    lateinit var appSetting: AppSettings

    var doubleBackToExitPressedOnce = false

    companion object {
        const val SNACKBARDURATION = 2000
        const val SETTINGS_REQUEST = 100
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ButterKnife.bind(this)
        setSupportActionBar(toolbar)

        if (savedInstanceState == null) {
            initializeFragment()
        }
        setupNavigationDrawer()
        setupViews()
    }

    override fun onResume() {
        super.onResume()
        presenter.onResume()
        presenter.getUserProfile()
    }

    override fun onPause() {
        super.onPause()
        presenter.onPause()
    }

    fun initializeFragment() {
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container,
                        TripsFragment()).commit()
    }

    fun setupNavigationDrawer() {
        val toggle = ActionBarDrawerToggle(
                this, drawer, toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close)
        drawer.addDrawerListener(toggle)
        toggle.syncState()
        navigationView.menu.performIdentifierAction(R.layout.fragment_trips, 0)
        navigationView.menu.getItem(0).isChecked = true
        navigationView.setNavigationItemSelectedListener(this)
    }

    fun setupViews() {
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
        val view = navigationView.getHeaderView(0)
        val header = view.find<LinearLayout>(R.id.header_content)
        header.setOnClickListener {
            startActivity<ProfileActivity>()
        }
    }

    override fun onGetProfileSuccess(user: UserDetail) {
        val view = navigationView.getHeaderView(0)
        val photo = view.find<ImageView>(R.id.iv_photo)
        val name = view.find<TextView>(R.id.tv_name)
        val email = view.find<TextView>(R.id.tv_email)

        Glide.with(this).load(user.photo).fitCenter().crossFade().into(photo)
        name.text = user.name
        email.text = user.email
    }

    override fun onGetProfileFailure(message: String) {
        TODO("not implemented")
    }

    fun navigateTo(fragment: Fragment) {
        val fragmentManager = fragmentManager
        fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit()
    }

    override fun onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {

            if (appSetting.preventExit()) {

                if (doubleBackToExitPressedOnce) {
                    super.onBackPressed()
                }
                doubleBackToExitPressedOnce = true
                Snackbar.make(fab, getString(R.string.activity_host_snackbar_prevent_close), SNACKBARDURATION).show()
                Handler().postDelayed({ doubleBackToExitPressedOnce = false }, SNACKBARDURATION.toLong())

            } else {
                super.onBackPressed()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == SETTINGS_REQUEST) {
            this.recreate()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_search -> {startActivity<SearchActivity>()}
            R.id.menu_help -> {}
        }
        return false
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_trips -> {
                navigateTo(trips)
                drawer.closeDrawer(GravityCompat.START)
            }
            R.id.nav_history -> {
                navigateTo(recent)
                drawer.closeDrawer(GravityCompat.START)
            }
            R.id.nav_settings -> {
                startActivityForResult<SettingsActivity>(SETTINGS_REQUEST)
            }
        }
        return true
    }

    override fun injectDependencies(applicationComponent: ApplicationComponent) {
        applicationComponent.plus(HostActivityModule(this))
                .injectTo(this)
    }
}
