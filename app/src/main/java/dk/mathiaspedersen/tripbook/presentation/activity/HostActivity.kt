package dk.mathiaspedersen.tripbook.presentation.activity

import android.app.ActivityManager
import android.app.Fragment
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.design.widget.*
import android.support.v4.view.GravityCompat
import android.support.v4.view.MenuItemCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import butterknife.BindView
import butterknife.ButterKnife
import com.bumptech.glide.Glide
import dk.mathiaspedersen.tripbook.R
import dk.mathiaspedersen.tripbook.presentation.entity.UserDetail
import dk.mathiaspedersen.tripbook.presentation.fragment.*
import dk.mathiaspedersen.tripbook.presentation.helper.AppSettings
import dk.mathiaspedersen.tripbook.presentation.injection.ApplicationComponent
import dk.mathiaspedersen.tripbook.presentation.injection.subcomponent.host.HostActivityModule
import dk.mathiaspedersen.tripbook.presentation.presenter.HostPresenter
import dk.mathiaspedersen.tripbook.presentation.service.Tracker
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
    lateinit var expenses: ExpenseFragment

    @Inject
    lateinit var archive: ArchiveFragment

    @Inject
    lateinit var auto: AutoFragment

    @Inject
    lateinit var report: ReportFragment

    @Inject
    lateinit var invite: InviteFragment

    @Inject
    lateinit var presenter: HostPresenter

    @Inject
    lateinit var appSetting: AppSettings

    val TRIPS = "TRIPS"
    val EXPENSES = "EXPENSES"
    val ARCHIVE = "ARCHIVE"
    val AUTOCLASSIFY = "AUTO"
    val REPORTS = "REPORTS"
    val INVITE = "INVITE"

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
        presenter.getUserProfile()
    }

    fun initializeFragment() {
        fragmentManager.beginTransaction().replace(R.id.fragment_container, TripsFragment()).commit()
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

    fun resetToolbarPosition() {
        val coordinator = findViewById(R.id.app_bar_coordinatior) as CoordinatorLayout
        val appbar = findViewById(R.id.app_bar_layout) as AppBarLayout
        val params = appbar.layoutParams as CoordinatorLayout.LayoutParams
        val behavior = params.behavior as AppBarLayout.Behavior
        behavior.onNestedFling(coordinator, appbar, null, 0.toFloat(), -1000.toFloat(), true)
    }

    fun setupViews() {
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

    fun isTrackingServiceRunning(): Boolean {
        val manager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        return manager.getRunningServices(Int.MAX_VALUE).any { it.service.className == Tracker::class.java.name }
    }

    override fun onGetProfileFailure(message: String) {
        TODO("not implemented")
    }

    fun navigateTo(fragment: Fragment, tag: String) {
        val fragmentManager = fragmentManager
        fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment, tag).commit()
        resetToolbarPosition()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.dispose()
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
            R.id.menu_help -> {
                startActivity<HelpActivity>()
            }
        }
        return false
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)

        val item = menu.findItem(R.id.track_switch)
        val toggle = MenuItemCompat.getActionView(item) as Switch

        toggle.isChecked = isTrackingServiceRunning()

        toggle.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                val stopIntent = Intent(application, Tracker::class.java)
                startService(stopIntent)

            } else {
                val stopIntent = Intent(application, Tracker::class.java)
                stopService(stopIntent)
            }
        }

        return true
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_trips -> {
                navigateTo(trips, TRIPS)
                drawer.closeDrawer(GravityCompat.START)
            }
            R.id.nav_expenses -> {
                navigateTo(expenses, EXPENSES)
                drawer.closeDrawer(GravityCompat.START)
            }
            R.id.nav_archive -> {
                navigateTo(archive, ARCHIVE)
                drawer.closeDrawer(GravityCompat.START)
            }
            R.id.nav_auto_classify -> {
                navigateTo(auto, AUTOCLASSIFY)
                drawer.closeDrawer(GravityCompat.START)
            }
            R.id.nav_report -> {
                navigateTo(report, REPORTS)
                drawer.closeDrawer(GravityCompat.START)
            }
            R.id.nav_invite -> {
                navigateTo(invite, INVITE)
                drawer.closeDrawer(GravityCompat.START)
            }
            R.id.nav_settings -> {
                startActivityForResult<SettingsActivity>(SETTINGS_REQUEST)
                item.isChecked = false
            }
        }
        return true
    }

    override fun injectDependencies(applicationComponent: ApplicationComponent) {
        applicationComponent.plus(HostActivityModule(this))
                .injectTo(this)
    }
}
