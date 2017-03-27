package dk.mathiaspedersen.tripbook.presentation.activity

import android.graphics.Point
import android.os.Build
import android.os.Bundle
import android.support.design.widget.BottomSheetBehavior
import android.support.v4.widget.NestedScrollView
import android.support.v7.app.AppCompatActivity
import android.util.Log
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
import dk.mathiaspedersen.tripbook.App
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

class DetailActivity : AppCompatActivity(), DetailView {

    @Inject
    lateinit var presenter: DetailPresenter

    @Inject
    lateinit var settings: AppSettings

    @BindView(R.id.detail_loader)
    lateinit var loader: FrameLayout

    @BindView(R.id.progressBar)
    lateinit var progress: ProgressBar

    @BindView(R.id.bottom_sheet)
    lateinit var bottomSheet: NestedScrollView

    @BindView(R.id.bottom_sheet_header)
    lateinit var bottomSheetHeader: LinearLayout

    @BindView(R.id.back_arrow)
    lateinit var backArrow: ImageView

    @BindView(R.id.time)
    lateinit var time: TextView

    private var map: SupportMapFragment? = null
    private var mBottomSheetBehavior: BottomSheetBehavior<View>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependencies(App.graph)
        setTheme(settings.getTranslucentTheme())
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        ButterKnife.bind(this)
        makeFullscreen()
        initializeMap()
        configureBottomSheet()
        requestCoordinates()
    }

    private fun configureBottomSheet() {
        val info = getDisplayInfo()
        loader.layoutParams.height = info.y / 2
        bottomSheet.layoutParams.height = info.y / 2
        mBottomSheetBehavior = BottomSheetBehavior.from(bottomSheet)
        mBottomSheetBehavior?.state = BottomSheetBehavior.STATE_EXPANDED
        mBottomSheetBehavior?.peekHeight = bottomSheetHeader.height
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
        val model = Parcels.unwrap<TripDetail>(intent.extras.getParcelable("model"))
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

    fun getDisplayInfo(): Point {
        val display = windowManager.defaultDisplay
        val size = Point()
        display.getSize(size)
        return size
    }

    override fun drawPolyline(trip: TripDetail, path: List<LatLng>, bounds: LatLngBounds) {
        map?.getMapAsync({

            PrettyTime().format(trip.time)

            it.setMapStyle(MapStyleOptions.loadRawResourceStyle(this, settings.getMapStyle()))
            it.addMarker(MarkerOptions().position(trip.start).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)).title("A"))
            it.addMarker(MarkerOptions().position(trip.end).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)).title("B"))
            it.addPolyline(settings.getPolylineStyle(path))
            it.setPadding(0, backArrow.height * 2, 0, bottomSheet.height)
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

            mBottomSheetBehavior?.setBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
                override fun onSlide(bottomSheet1: View, slideOffset: Float) {
                    Log.d("FIBER", "Height: ${bottomSheet1.measuredHeight}")
                }

                override fun onStateChanged(bottomSheet: View, newState: Int) {}
            })


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
