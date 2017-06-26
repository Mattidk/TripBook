package dk.mathiaspedersen.tripbook.presentation.fragment

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.design.widget.FloatingActionButton
import android.support.v4.view.MenuItemCompat
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.support.v7.widget.helper.ItemTouchHelper
import android.util.Log
import android.view.*
import android.widget.CompoundButton
import android.widget.ProgressBar
import android.widget.Switch
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import dk.mathiaspedersen.tripbook.R
import dk.mathiaspedersen.tripbook.presentation.activity.DetailActivity
import dk.mathiaspedersen.tripbook.presentation.activity.VehicleDetailActivity
import dk.mathiaspedersen.tripbook.presentation.custom.SwipeHelperCallback
import dk.mathiaspedersen.tripbook.presentation.custom.TripAdapter
import dk.mathiaspedersen.tripbook.presentation.custom.TripItemAnimator
import dk.mathiaspedersen.tripbook.presentation.entity.TripDetail
import dk.mathiaspedersen.tripbook.presentation.entity.TripStackDetal
import dk.mathiaspedersen.tripbook.presentation.helper.AppSettings
import dk.mathiaspedersen.tripbook.presentation.injection.ApplicationComponent
import dk.mathiaspedersen.tripbook.presentation.injection.subcomponent.trips.TripsFragmentModule
import dk.mathiaspedersen.tripbook.presentation.presenter.TripsPresenter
import dk.mathiaspedersen.tripbook.presentation.service.Tracker
import dk.mathiaspedersen.tripbook.presentation.view.TripsView
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity
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
        initSwipeRefreshLayout()
        setScrollFlags()
        showToolbar()
        setupFab()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        swipeRefreshLayout.setOnRefreshListener { getTrips() }
        presenter.getTrips()
        swipeRefreshLayout.isRefreshing = false
        showToolbar()
    }

    fun setupViews() {
        val mLayoutManager = LinearLayoutManager(activity)
        tripsRecyclerView.setHasFixedSize(true)
        tripsRecyclerView.layoutManager = mLayoutManager
        tripsRecyclerView.adapter = adapter
        val callback = SwipeHelperCallback(adapter)
        val mItemTouchHelper = ItemTouchHelper(callback)
        mItemTouchHelper.attachToRecyclerView(tripsRecyclerView)
    }

    fun initSwipeRefreshLayout() {
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

    fun setupFab(){
        val fab = activity?.find<FloatingActionButton>(R.id.fab)
        fab?.setOnClickListener { presenter.popTrip() }
    }

    override fun resetCounters(message: String) {
        val numTrips = activity?.find<TextView>(R.id.num_trips)
        val numMiles = activity?.find<TextView>(R.id.num_miles)
        val numValue = activity?.find<TextView>(R.id.num_value)

        numTrips?.text = message
        numMiles?.text = message
        numValue?.text = message
    }

    override fun populateRecyclerView(trips: ArrayList<TripDetail>) {
        tripsRecyclerView.itemAnimator = TripItemAnimator()
        swipeRefreshLayout.isRefreshing = false
        spinner.visibility = View.GONE
        adapter.refresh(trips)
    }

    override fun setTripText(message: String) {
        val tripCount = activity?.find<TextView>(R.id.num_trips)
        tripCount?.text = message
    }

    override fun setMilesText(message: String) {
        val milesCount = activity?.find<TextView>(R.id.num_miles)
        milesCount?.text = message
    }

    override fun setValueText(message: String) {
        val valueCount = activity?.find<TextView>(R.id.num_value)
        valueCount?.text = message
    }

    override fun unableToFetchTrips(message: String) {
        TODO("not implemented")
    }

    override fun showUndoSnackBar(description: String) {
        TODO("not implemented")
    }

    override fun hideUndoSnackBar() {
        TODO("not implemented")
    }

    override fun pushTrip(trip: TripStackDetal) {
        presenter.pushTrip(trip)
    }

    override fun popTrip(trip: TripStackDetal) {
        adapter.addTrip(trip, tripsRecyclerView)
    }

    override fun showMapDetail(key: String) {
        startActivity<DetailActivity>("key" to key)
    }

    override fun showVehicleDetail(key: String, name: String) {
        startActivity<VehicleDetailActivity>("key" to key, "name" to name)
    }

    override fun deleteTrip(key: String) {
        TODO("not implemented")
    }

    override fun changeVehicle(key: String) {
        TODO("not implemented")
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_refresh -> { getTrips() }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun setTitle() {
        activity.title = getString(R.string.fragment_trips_title)
    }

    override fun onDetach() {
        super.onDetach()
        hideToolbar()
        presenter.dispose()
    }

    fun getTrips() {
        presenter.clearStack()
        presenter.getTrips()
        presenter.resetCounters()
        swipeRefreshLayout.isRefreshing = true
    }

    override fun injectDependencies(applicationComponent: ApplicationComponent) {
        applicationComponent.plus(TripsFragmentModule(this))
                .injectTo(this)
    }
}