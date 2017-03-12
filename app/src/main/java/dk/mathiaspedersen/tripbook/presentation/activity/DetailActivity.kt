package dk.mathiaspedersen.tripbook.presentation.activity

import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import butterknife.BindView
import butterknife.ButterKnife
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MapStyleOptions
import dk.mathiaspedersen.tripbook.R
import dk.mathiaspedersen.tripbook.presentation.helper.AppSettings
import dk.mathiaspedersen.tripbook.presentation.injection.ApplicationComponent
import dk.mathiaspedersen.tripbook.presentation.injection.subcomponent.detail.DetailActivityModule
import dk.mathiaspedersen.tripbook.presentation.presenter.DetailPresenter
import dk.mathiaspedersen.tripbook.presentation.view.DetailView
import org.jetbrains.anko.dip
import javax.inject.Inject


class DetailActivity : BaseActivity(), DetailView {

    override val layoutResource: Int = R.layout.activity_detail

    @Inject
    lateinit var presenter: DetailPresenter

    @Inject
    lateinit var settings: AppSettings

    @BindView(R.id.toolbar)
    lateinit var toolbar: Toolbar

    private var map: SupportMapFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ButterKnife.bind(this)
        configureActionBar()
        initializeMap()
        requestCoordinates()
    }

    override fun onResume() {
        super.onResume()
        presenter.onResume()
    }

    override fun onPause() {
        super.onPause()
        presenter.onPause()
    }

    private fun requestCoordinates() {
        val model = intent.extras.getString("coordinates")
        if (model != null) {
            presenter.prepareMap(model)
        }
    }

    private fun initializeMap() {
        map = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
    }

    private fun configureActionBar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun drawPolyline(path: MutableList<LatLng>, bounds: LatLngBounds) {
        map?.getMapAsync({
            it.setMapStyle(MapStyleOptions.loadRawResourceStyle(this, settings.getMapStyle()))
            it.addPolyline(settings.getPolylineStyle(path))
            it.setPadding(0, toolbar.height, 0, 0)
            it.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, dip(32)))
        })
    }

    override fun injectDependencies(applicationComponent: ApplicationComponent) {
        applicationComponent.plus(DetailActivityModule(this))
                .injectTo(this)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                overridePendingTransition(R.anim.activity_slide_in_left, R.anim.activity_slide_out_right)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
