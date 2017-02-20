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
import dk.mathiaspedersen.tripbook.R
import dk.mathiaspedersen.tripbook.presentation.fragment.HistoryFragment
import dk.mathiaspedersen.tripbook.presentation.fragment.TripsFragment
import dk.mathiaspedersen.tripbook.presentation.injection.ApplicationComponent
import dk.mathiaspedersen.tripbook.presentation.injection.subcomponent.container.ContainerActivityModule
import dk.mathiaspedersen.tripbook.presentation.presenter.MainPresenter
import dk.mathiaspedersen.tripbook.presentation.view.MainView
import org.jetbrains.anko.find
import javax.inject.Inject

class ContainerActivity : BaseActivity(), MainView, NavigationView.OnNavigationItemSelectedListener {

    override val layoutResource = R.layout.activity_container

    val drawer: DrawerLayout by lazy { find<DrawerLayout>(R.id.drawer_layout) }

    @Inject
    lateinit var presenter: MainPresenter

    @Inject
    lateinit var trips: TripsFragment

    @Inject
    lateinit var history: HistoryFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            addTripsFragment()
        }
        setupNavigationDrawer()
    }

    override fun injectDependencies(applicationComponent: ApplicationComponent) {
        applicationComponent.plus(ContainerActivityModule(this))
                .injectTo(this)
    }

    fun addTripsFragment() {
        fragmentManager.beginTransaction()
                .add(R.id.fragment_container,
                        TripsFragment()).commit()
    }

    fun setupNavigationDrawer() {
        val toggle = ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer.addDrawerListener(toggle)
        toggle.syncState()

        val navigationView = findViewById(R.id.nav_view) as NavigationView
        navigationView.menu.performIdentifierAction(R.layout.fragment_trips, 0)
        navigationView.menu.getItem(0).isChecked = true
        navigationView.setNavigationItemSelectedListener(this)
    }

    override fun setupViews() {
        val fab = findViewById(R.id.fab) as FloatingActionButton
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
    }

    override fun example(example: String) {}

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId


        if (id == R.id.action_settings) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        val id = item.itemId

        if (id == R.id.nav_camera) {
            navigateTo(trips)
        } else if (id == R.id.nav_gallery) {
            navigateTo(history)
        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        drawer.closeDrawer(GravityCompat.START)
        return true
    }
}
