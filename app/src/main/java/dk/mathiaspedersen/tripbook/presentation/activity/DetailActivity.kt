package dk.mathiaspedersen.tripbook.presentation.activity

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MapStyleOptions
import dk.mathiaspedersen.tripbook.App
import dk.mathiaspedersen.tripbook.R
import dk.mathiaspedersen.tripbook.presentation.helper.AppSettings
import dk.mathiaspedersen.tripbook.presentation.injection.ApplicationComponent
import dk.mathiaspedersen.tripbook.presentation.injection.subcomponent.detail.DetailActivityModule
import dk.mathiaspedersen.tripbook.presentation.presenter.DetailPresenter
import dk.mathiaspedersen.tripbook.presentation.util.supportsMarshmallow
import dk.mathiaspedersen.tripbook.presentation.view.DetailView
import org.jetbrains.anko.dip
import javax.inject.Inject


class DetailActivity : AppCompatActivity(), DetailView {

    @Inject
    lateinit var presenter: DetailPresenter

    @Inject
    lateinit var settings: AppSettings

    @BindView(R.id.detail_loader)
    lateinit var loader: FrameLayout

    @BindView(R.id.back_arrow)
    lateinit var backArrow: ImageView

    private var map: SupportMapFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependencies(App.graph)
        setTheme(settings.getTranslucentTheme())
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        ButterKnife.bind(this)
        makeFullscreen()
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

    private fun makeFullscreen() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !settings.isThemeDark()){
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_IMMERSIVE or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }else{
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_IMMERSIVE
        }
    }

    override fun drawPolyline(path: MutableList<LatLng>, bounds: LatLngBounds) {
        map?.getMapAsync({
            it.setMapStyle(MapStyleOptions.loadRawResourceStyle(this, settings.getMapStyle()))
            it.addPolyline(settings.getPolylineStyle(path))
            it.setPadding(0, backArrow.height, 0, 0)
            it.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, dip(32)))
            it.setOnMapLoadedCallback {
                loader.visibility = View.GONE
            }
        })
    }

    @OnClick(R.id.back_arrow)
    fun navigateBack(view: View) {
        finish()
    }

    fun injectDependencies(applicationComponent: ApplicationComponent) {
        applicationComponent.plus(DetailActivityModule(this))
                .injectTo(this)
    }

}
