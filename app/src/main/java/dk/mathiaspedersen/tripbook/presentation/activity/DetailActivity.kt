package dk.mathiaspedersen.tripbook.presentation.activity

import android.graphics.Point
import android.os.Build
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.design.widget.CollapsingToolbarLayout
import android.support.design.widget.CoordinatorLayout
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.*
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import dk.mathiaspedersen.tripbook.R
import dk.mathiaspedersen.tripbook.presentation.entity.TripDetail
import dk.mathiaspedersen.tripbook.presentation.helper.AppSettings
import dk.mathiaspedersen.tripbook.presentation.injection.ApplicationComponent
import dk.mathiaspedersen.tripbook.presentation.injection.subcomponent.detail.DetailActivityModule
import dk.mathiaspedersen.tripbook.presentation.presenter.DetailPresenter
import dk.mathiaspedersen.tripbook.presentation.view.DetailView
import org.jetbrains.anko.dip
import org.ocpsoft.prettytime.PrettyTime
import org.parceler.Parcels
import javax.inject.Inject

class DetailActivity : BaseActivity(), DetailView {

    override val layoutResource: Int = R.layout.activity_detail

    @Inject
    lateinit var presenter: DetailPresenter

    @Inject
    lateinit var settings: AppSettings

    @BindView(R.id.detail_loader)
    lateinit var loader: FrameLayout

    @BindView(R.id.progress_bar)
    lateinit var progress: ProgressBar

    @BindView(R.id.navigate_up)
    lateinit var backArrow: ImageView

    @BindView(R.id.time)
    lateinit var time: TextView

    @BindView(R.id.app_bar)
    lateinit var appbar: AppBarLayout

    @BindView(R.id.content_header)
    lateinit var header: RelativeLayout

    @BindView(R.id.toolbar_layout)
    lateinit var collapsingToolbar: CollapsingToolbarLayout

    private var map: SupportMapFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ButterKnife.bind(this)
        makeFullscreen()
        initializeMap()
        requestCoordinates()
        disableScrollOnMap()
    }

    override fun onResume() {
        super.onResume()
        presenter.onResume()
    }

    override fun onPause() {
        super.onPause()
        presenter.onPause()
    }

    fun positionContentHeader() {
        val displayInfo = getDisplayInfo()
        collapsingToolbar.minimumHeight = displayInfo.y / 2
        appbar.layoutParams.height = displayInfo.y - header.height
    }

    private fun initializeMap() {
        map = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        positionContentHeader()
    }

    private fun requestCoordinates() {
        val model = Parcels.unwrap<TripDetail>(intent.extras.getParcelable("model"))
        if (model != null) {
            presenter.prepareMap(model)
            time.text = PrettyTime().format(model.time)
        }
    }

    private fun disableScrollOnMap() {
        val params = appbar.layoutParams as CoordinatorLayout.LayoutParams
        val behavior = AppBarLayout.Behavior()
        behavior.setDragCallback(object : AppBarLayout.Behavior.DragCallback() {
            override fun canDrag(appBarLayout: AppBarLayout): Boolean {
                return false
            }
        })
        params.behavior = behavior
    }

    private fun makeFullscreen() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !settings.isThemeDark()) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_IMMERSIVE or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        } else {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_IMMERSIVE
        }
    }

    fun getDisplayInfo(): Point {
        val display = windowManager.defaultDisplay
        val size = Point()
        display.getSize(size)
        return size
    }

    override fun drawPolyline(trip: TripDetail, path: List<LatLng>, bounds: LatLngBounds) {
        map?.getMapAsync({
            it.setMapStyle(MapStyleOptions.loadRawResourceStyle(this, settings.getMapStyle()))
            it.addMarker(MarkerOptions().position(trip.start).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)).title("A"))
            it.addMarker(MarkerOptions().position(trip.end).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)).title("B"))
            it.addPolyline(settings.getPolylineStyle(path))
            it.setPadding(0, backArrow.height * 2, 0, 0)
            it.uiSettings.isCompassEnabled = false
            it.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, dip(32)))
            it.setOnMapLoadedCallback {
                progress.visibility = View.INVISIBLE
                val mLoadAnimation = AnimationUtils.loadAnimation(applicationContext, android.R.anim.fade_out)
                mLoadAnimation.duration = 200
                mLoadAnimation.setAnimationListener(object : Animation.AnimationListener {
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

    @OnClick(R.id.navigate_up)
    fun navigateBack(view: View) {
        finish()
    }

    override fun injectDependencies(applicationComponent: ApplicationComponent) {
        applicationComponent.plus(DetailActivityModule(this))
                .injectTo(this)
    }

}
