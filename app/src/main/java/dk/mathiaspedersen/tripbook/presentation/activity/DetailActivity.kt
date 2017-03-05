package dk.mathiaspedersen.tripbook.presentation.activity

import android.content.res.Resources
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import butterknife.ButterKnife
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.android.gms.maps.model.PolylineOptions
import dk.mathiaspedersen.tripbook.R
import dk.mathiaspedersen.tripbook.presentation.injection.ApplicationComponent
import dk.mathiaspedersen.tripbook.presentation.injection.subcomponent.detail.DetailActivityModule
import dk.mathiaspedersen.tripbook.presentation.presenter.DetailPresenter
import dk.mathiaspedersen.tripbook.presentation.view.DetailView
import javax.inject.Inject


class DetailActivity : BaseActivity(), DetailView {
    override val layoutResource: Int = R.layout.activity_detail

    @Inject
    lateinit var presenter: DetailPresenter

    private var mapFragment: SupportMapFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ButterKnife.bind(this)
        mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        actionBar

        val model = intent.extras.getString("coordinates")

        if (model != null) {
            presenter.prepareMap(model)
        }
    }

    override fun onResume() {
        super.onResume()
        presenter.onResume()
    }

    override fun onPause() {
        super.onPause()
        presenter.onPause()
    }

    override fun drawPolyline(path: MutableList<LatLng>, bounds: LatLngBounds) {

        mapFragment?.getMapAsync({

            try {
                val success = it.setMapStyle(MapStyleOptions.loadRawResourceStyle(this, R.raw.gowalla))
                if (!success) { Log.d("GODA", "Style parsing failed") }
            } catch (e: Resources.NotFoundException) {
                Log.d("GODA", "Can't find style. Error: ", e)
            }

            it.addPolyline(PolylineOptions().addAll(path).width(15.toFloat()).color(Color.BLUE))
            it.setPadding(20, 20, 20, 100)
            it.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, 0))

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
                overridePendingTransition(R.anim.activity_slide_in_left, R.anim.activity_slide_out_right);
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
}
