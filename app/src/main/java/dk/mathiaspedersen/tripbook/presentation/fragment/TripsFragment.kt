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
import android.view.*
import android.widget.ProgressBar
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import dk.mathiaspedersen.tripbook.R
import dk.mathiaspedersen.tripbook.presentation.custom.SwipeHelperCallback
import dk.mathiaspedersen.tripbook.presentation.custom.TripAdapter
import dk.mathiaspedersen.tripbook.presentation.custom.TripItemAnimator
import dk.mathiaspedersen.tripbook.presentation.entity.TripDetail
import dk.mathiaspedersen.tripbook.presentation.helper.AppSettings
import dk.mathiaspedersen.tripbook.presentation.injection.ApplicationComponent
import dk.mathiaspedersen.tripbook.presentation.injection.subcomponent.trips.TripsFragmentModule
import dk.mathiaspedersen.tripbook.presentation.presenter.TripsPresenter
import dk.mathiaspedersen.tripbook.presentation.view.TripsView
import org.jetbrains.anko.find
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

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
            getTrips()
        }
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
        val numTrips: TextView = activity.find(R.id.num_trips)
        val numMiles: TextView = activity.find(R.id.num_miles)
        val numValue: TextView = activity.find(R.id.num_value)

        numTrips.text = "0"
        numMiles.text = "0"
        numValue.text = String.format(getString(R.string.toolbar_value), 0)
    }

    override fun populateRecyclerView(trips: ArrayList<TripDetail>) {
        tripsRecyclerView.itemAnimator = TripItemAnimator()
        swipeRefreshLayout.isRefreshing = false
        spinner.visibility = View.GONE
        adapter.refresh(trips)
    }

    override fun SumValue(value: Double) {
        val numValue = activity.find<TextView>(R.id.num_value)
        val animatorValue = ValueAnimator()
        animatorValue.setObjectValues(0, Math.round(value))
        animatorValue.addUpdateListener({
            numValue.text = String.format(getString(R.string.toolbar_value), it.animatedValue)
        })
        animatorValue.setEvaluator(object : TypeEvaluator<Int> {
            override fun evaluate(fraction: Float, startValue: Int, endValue: Int): Int {
                return Math.round(startValue + (endValue - startValue) * fraction)
            }
        })
        animatorValue.duration = 1000
        animatorValue.start()
    }

    override fun SumMiles(miles: Double) {
        val numMiles = activity.find<TextView>(R.id.num_miles)
        val animator = ValueAnimator()
        animator.setObjectValues(0, Math.round(miles))
        animator.addUpdateListener({ numMiles.text = it.animatedValue.toString() })
        animator.setEvaluator(object : TypeEvaluator<Int> {
            override fun evaluate(fraction: Float, startValue: Int, endValue: Int): Int {
                return Math.round(startValue + (endValue - startValue) * fraction)
            }
        })
        animator.duration = 1000
        animator.start()
    }

    override fun SumTrips(trips: Int) {
        val numTrips = activity.find<TextView>(R.id.num_trips)
        val animator = ValueAnimator()
        animator.setObjectValues(0, trips)
        animator.addUpdateListener({ numTrips.text = it.animatedValue.toString() })
        animator.setEvaluator(object : TypeEvaluator<Int> {
            override fun evaluate(fraction: Float, startValue: Int, endValue: Int): Int {
                return Math.round(startValue + (endValue - startValue) * fraction)
            }
        })
        animator.duration = 1000
        animator.start()
    }

    override fun unableToFetchTrips(message: String) {
        // Temporarily empty
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(R.menu.main, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_refresh -> { getTrips() }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onResume() {
        super.onResume()
        swipeRefreshLayout.isRefreshing = false
        presenter.onResume()
        showToolbar()
    }

    override fun onPause() {
        super.onPause()
        adapter.saveChanges()
        presenter.onPause()
    }

    override fun setTitle() {
        activity.title = getString(R.string.fragment_trips_title)
    }

    override fun onDetach() {
        super.onDetach()
        hideToolbar()
    }

    fun getTrips() {
        presenter.getUnclassifiedTrips()
        presenter.clearRecyclerView()
        swipeRefreshLayout.isRefreshing = true
        resetTripsValues()
    }

    override fun injectDependencies(applicationComponent: ApplicationComponent) {
        applicationComponent.plus(TripsFragmentModule(this))
                .injectTo(this)
    }
}