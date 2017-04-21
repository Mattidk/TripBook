package dk.mathiaspedersen.tripbook.presentation.fragment

import android.animation.TypeEvaluator
import android.animation.ValueAnimator
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import dk.mathiaspedersen.tripbook.R
import dk.mathiaspedersen.tripbook.presentation.custom.SwipeHelperCallback
import dk.mathiaspedersen.tripbook.presentation.custom.TripAdapter
import dk.mathiaspedersen.tripbook.presentation.entity.TripDetail
import dk.mathiaspedersen.tripbook.presentation.helper.AppSettings
import dk.mathiaspedersen.tripbook.presentation.injection.ApplicationComponent
import dk.mathiaspedersen.tripbook.presentation.injection.subcomponent.trips.TripsFragmentModule
import dk.mathiaspedersen.tripbook.presentation.presenter.TripsPresenter
import dk.mathiaspedersen.tripbook.presentation.view.TripsView
import java.text.DecimalFormat
import java.util.*
import javax.inject.Inject


class TripsFragment : BaseFragment(), TripsView {

    @Inject
    lateinit var presenter: TripsPresenter

    @Inject
    lateinit var settings: AppSettings

    @Inject
    lateinit var adapter: TripAdapter

    @BindView(R.id.loading_spinner)
    lateinit var spinner: ProgressBar

    @BindView(R.id.trips_recyclerview)
    lateinit var tripsRecyclerView: RecyclerView

    @BindView(R.id.refresh_container)
    lateinit var swipeRefreshLayout: SwipeRefreshLayout

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.fragment_trips, container, false)
        ButterKnife.bind(this, view)
        setupViews()
        setScrollFlags()
        showToolbar()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        swipeRefreshLayout.setOnRefreshListener {
            adapter.saveChanges()
            fetchTrips()
        }
    }

    fun setScrollFlags() {
        val toolbar = activity.findViewById(R.id.toolbar) as Toolbar
        val params = toolbar.layoutParams as AppBarLayout.LayoutParams
        params.scrollFlags = AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL or AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS
    }

    fun showToolbar() {
        val status = activity.findViewById(R.id.status_toolbar)
        status.visibility = View.VISIBLE
    }

    fun hideToolbar() {
        val status = activity.findViewById(R.id.status_toolbar)
        status.visibility = View.GONE
    }

    fun resetTripsValues() {
        val numTrips = activity.findViewById(R.id.num_trips) as TextView
        val numMiles = activity.findViewById(R.id.num_miles) as TextView
        val numValue = activity.findViewById(R.id.num_value) as TextView

        numTrips.text = "0"
        numMiles.text = "0"
        numValue.text = "0"
    }

    override fun populateRecyclerView(trips: ArrayList<TripDetail>) {
        spinner.visibility = View.GONE
        swipeRefreshLayout.isRefreshing = false
        adapter.refresh(trips)
    }

    override fun displaySums(trips: Int, miles: Double, value: Double) {
        val numTrips = activity.findViewById(R.id.num_trips) as TextView
        val numMiles = activity.findViewById(R.id.num_miles) as TextView
        val numValue = activity.findViewById(R.id.num_value) as TextView

        val formatMiles = DecimalFormat("#.#")
        val formatValue = DecimalFormat("#.##")

        val animatorTrips = ValueAnimator()
        animatorTrips.setObjectValues(0, trips)
        animatorTrips.addUpdateListener({
            numTrips.text = it.animatedValue.toString()
        })
        animatorTrips.setEvaluator(object : TypeEvaluator<Int> {
            override fun evaluate(fraction: Float, startValue: Int, endValue: Int): Int {
                return Math.round(startValue + (endValue - startValue) * fraction)
            }
        })
        animatorTrips.duration = 1000
        animatorTrips.start()

        val animatorMiles = ValueAnimator()
        animatorMiles.setObjectValues(0, miles)
        animatorMiles.addUpdateListener({ numMiles.text = formatMiles.format(it.animatedValue) })
        animatorMiles.setEvaluator(object : TypeEvaluator<Double> {
            override fun evaluate(fraction: Float, startValue: Double, endValue: Double): Double {
                return Math.round(startValue + (endValue - startValue) * fraction).toDouble()
            }
        })
        animatorMiles.duration = 1000
        animatorMiles.start()

        val animatorValue = ValueAnimator()
        animatorValue.setObjectValues(0, value)
        animatorValue.addUpdateListener({ numValue.text = formatValue.format(it.animatedValue) })
        animatorValue.setEvaluator(object : TypeEvaluator<Double> {
            override fun evaluate(fraction: Float, startValue: Double, endValue: Double): Double {
                return Math.round(startValue + (endValue - startValue) * fraction).toDouble()
            }
        })
        animatorValue.duration = 1000
        animatorValue.start()
    }

    override fun unableToFetchTrips(message: String) {
        // Temporarily empty
    }

    override fun onResume() {
        super.onResume()
        swipeRefreshLayout.isRefreshing = false
        presenter.onResume()
        resetTripsValues()
        showToolbar()
    }

    override fun onPause() {
        super.onPause()
        adapter.saveChanges()
        presenter.onPause()
    }

    override fun onDetach() {
        super.onDetach()
        hideToolbar()
    }

    fun fetchTrips() {
        if (!swipeRefreshLayout.isRefreshing) {
            swipeRefreshLayout.isRefreshing = true
        }
        resetTripsValues()
        presenter.getUnclassifiedTrips()
    }

    fun setupViews() {

        activity.title = "Trips"

        tripsRecyclerView.setHasFixedSize(true)
        val mLayoutManager = LinearLayoutManager(activity)
        tripsRecyclerView.layoutManager = mLayoutManager
        tripsRecyclerView.adapter = adapter

        val callback = SwipeHelperCallback(adapter, tripsRecyclerView)
        val mItemTouchHelper = ItemTouchHelper(callback)
        mItemTouchHelper.attachToRecyclerView(tripsRecyclerView)

        swipeRefreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light)

        swipeRefreshLayout.setColorSchemeResources(
                R.color.colorAccent,
                R.color.colorAccent,
                R.color.colorAccent,
                R.color.colorPrimary)
    }

    override fun injectDependencies(applicationComponent: ApplicationComponent) {
        applicationComponent.plus(TripsFragmentModule(this))
                .injectTo(this)
    }
}
