package dk.mathiaspedersen.tripbook.presentation.activity

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.NavigationView
import android.support.design.widget.Snackbar
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.view.Menu
import android.view.MenuItem
import butterknife.BindView
import butterknife.ButterKnife
import dk.mathiaspedersen.tripbook.R
import dk.mathiaspedersen.tripbook.domain.entity.Trip
import dk.mathiaspedersen.tripbook.presentation.fragment.HistoryFragment
import dk.mathiaspedersen.tripbook.presentation.fragment.TripsFragment
import dk.mathiaspedersen.tripbook.presentation.injection.ApplicationComponent
import dk.mathiaspedersen.tripbook.presentation.injection.subcomponent.container.ContainerActivityModule
import dk.mathiaspedersen.tripbook.presentation.presenter.MainPresenter
import dk.mathiaspedersen.tripbook.presentation.view.MainView
import javax.inject.Inject

class ContainerActivity : BaseActivity(), MainView, NavigationView.OnNavigationItemSelectedListener {

    override val layoutResource = R.layout.activity_container
    @Inject lateinit var trips: TripsFragment
    @Inject lateinit var history: HistoryFragment
    @Inject lateinit var presenter: MainPresenter
    @BindView(R.id.drawer_layout) lateinit var drawer: DrawerLayout
    @BindView(R.id.nav_view) lateinit var navigationView: NavigationView
    @BindView(R.id.fab) lateinit var fab: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ButterKnife.bind(this)
        if (savedInstanceState == null) {
            initializeFragment()
        }
        setupNavigationDrawer()
        setupViews()
    }

    override fun injectDependencies(applicationComponent: ApplicationComponent) {
        applicationComponent.plus(ContainerActivityModule(this))
                .injectTo(this)
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
    }

    override fun example(response: List<Trip>) {
        // Temporarily empty
    }

    override fun onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_settings -> return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_camera -> navigateTo(trips)
            R.id.nav_gallery -> navigateTo(history)
        }
        drawer.closeDrawer(GravityCompat.START)
        return true
    }
}
