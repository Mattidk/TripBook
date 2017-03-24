package dk.mathiaspedersen.tripbook.presentation.activity

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.ViewAnimationUtils
import android.widget.FrameLayout
import android.widget.ImageView
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.SupportMapFragment
import dk.mathiaspedersen.tripbook.App
import dk.mathiaspedersen.tripbook.R
import dk.mathiaspedersen.tripbook.presentation.helper.AppSettings
import dk.mathiaspedersen.tripbook.presentation.injection.ApplicationComponent
import dk.mathiaspedersen.tripbook.presentation.injection.subcomponent.detail.DetailActivityModule
import dk.mathiaspedersen.tripbook.presentation.presenter.DetailPresenter
import dk.mathiaspedersen.tripbook.presentation.util.supportsLollipop
import dk.mathiaspedersen.tripbook.presentation.view.DetailView
import org.jetbrains.anko.dip
import javax.inject.Inject
import android.view.animation.AnimationUtils
import android.view.animation.Animation
import android.widget.ProgressBar
import com.google.android.gms.maps.model.*
import dk.mathiaspedersen.tripbook.presentation.util.staticmaps.map.StaticMap


class DetailActivity : AppCompatActivity(), DetailView {

    @Inject
    lateinit var presenter: DetailPresenter

    @Inject
    lateinit var settings: AppSettings

    @BindView(R.id.detail_loader)
    lateinit var loader: FrameLayout

    @BindView(R.id.progressBar)
    lateinit var progress: ProgressBar

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

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !settings.isThemeDark()) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_IMMERSIVE or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        } else {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_IMMERSIVE
        }
    }

    override fun drawPolyline(start: LatLng, end: LatLng, path: MutableList<LatLng>, bounds: LatLngBounds) {
        map?.getMapAsync({
            it.setMapStyle(MapStyleOptions.loadRawResourceStyle(this, settings.getMapStyle()))
            it.addMarker(MarkerOptions().position(start).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)).title("A"))
            it.addMarker(MarkerOptions().position(end).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)).title("B"))
            it.addPolyline(settings.getPolylineStyle(path))
            it.setPadding(0, backArrow.height, 0, 0)
            it.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, dip(32)))
            it.setOnMapLoadedCallback {
                progress.visibility = View.INVISIBLE
                val mLoadAnimation = AnimationUtils.loadAnimation(applicationContext, android.R.anim.fade_out)
                mLoadAnimation.duration = 200
                mLoadAnimation.setAnimationListener(object: Animation.AnimationListener {
                    override fun onAnimationRepeat(animation: Animation?) {}
                    override fun onAnimationStart(animation: Animation?) {}
                    override fun onAnimationEnd(animation: Animation?) {
                        loader.visibility = View.INVISIBLE
                    }
                })
                loader.startAnimation(mLoadAnimation)
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
