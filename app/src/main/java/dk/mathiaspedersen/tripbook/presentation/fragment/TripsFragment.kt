package dk.mathiaspedersen.tripbook.presentation.fragment

import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.RelativeLayout
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
import java.util.*
import javax.inject.Inject


class TripsFragment : BaseFragment(), TripsView {

    @Inject
    lateinit var presenter: TripsPresenter

    @Inject
    lateinit var settings: AppSettings

    @Inject
    lateinit var adapter: TripAdapter

    @BindView(R.id.noItems)
    lateinit var noItems: RelativeLayout

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
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        swipeRefreshLayout.setOnRefreshListener {
            adapter.saveChanges()
            fetchTrips()
        }
    }

    override fun populateRecyclerView(trips: ArrayList<TripDetail>) {
        if (trips.isEmpty()) {
            noItems.visibility = View.VISIBLE
        }else {
            noItems.visibility = View.GONE
        }
        spinner.visibility = View.GONE
        swipeRefreshLayout.isRefreshing = false
        adapter.refresh(trips)
    }

    override fun unableToFetchTrips(message: String) {
        // Temporarily empty
    }

    override fun onResume() {
        super.onResume()
        swipeRefreshLayout.isRefreshing = false
        presenter.onResume()
    }

    override fun onPause() {
        super.onPause()
        adapter.saveChanges()
        presenter.onPause()
    }

    fun fetchTrips() {
        if (!swipeRefreshLayout.isRefreshing) {
            swipeRefreshLayout.isRefreshing = true
            noItems.visibility = View.GONE
        }
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
