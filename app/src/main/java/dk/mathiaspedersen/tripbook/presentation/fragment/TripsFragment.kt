package dk.mathiaspedersen.tripbook.presentation.fragment

import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import butterknife.BindView
import butterknife.ButterKnife
import dk.mathiaspedersen.tripbook.R
import dk.mathiaspedersen.tripbook.domain.entity.Trip
import dk.mathiaspedersen.tripbook.presentation.custom.TripsListAdapter
import dk.mathiaspedersen.tripbook.presentation.injection.ApplicationComponent
import dk.mathiaspedersen.tripbook.presentation.injection.subcomponent.trips.TripsFragmentModule
import dk.mathiaspedersen.tripbook.presentation.presenter.TripsPresenter
import dk.mathiaspedersen.tripbook.presentation.view.TripsView
import javax.inject.Inject

class TripsFragment : BaseFragment(), TripsView {
    @Inject lateinit var presenter: TripsPresenter

    @BindView(R.id.loading_spinner) lateinit var spinner: ProgressBar
    @BindView(R.id.trips_recyclerview) lateinit var mRecyclerView: RecyclerView
    @BindView(R.id.refresh_container) lateinit var mSwipeRefreshLayout: SwipeRefreshLayout
    val mAdapter = TripsListAdapter(listOf<Trip>())

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mSwipeRefreshLayout.setOnRefreshListener { fetchTrips() }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.fragment_trips, container, false)
        ButterKnife.bind(this, view)
        setupViews()
        return view
    }

    override fun example(response: List<Trip>) {
        spinner.visibility = View.GONE
        mSwipeRefreshLayout.isRefreshing = false
        mAdapter.refresh(response)
    }

    override fun onError(message: String) {
        // Temporarily empty
    }

    override fun onResume() {
        super.onResume()
        mSwipeRefreshLayout.isRefreshing = false
        presenter.onResume()
    }

    override fun onPause() {
        super.onPause()
        presenter.onPause()
    }

    fun fetchTrips() {
        if (!mSwipeRefreshLayout.isRefreshing) {
            mSwipeRefreshLayout.isRefreshing = true
        }
        presenter.getUnclassifiedTrips()
    }

    fun setupViews() {
        mRecyclerView.setHasFixedSize(true)
        val mLayoutManager = LinearLayoutManager(activity)
        mRecyclerView.layoutManager = mLayoutManager
        mRecyclerView.adapter = mAdapter

        mSwipeRefreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light)
    }

    override fun injectDependencies(applicationComponent: ApplicationComponent) {
        applicationComponent.plus(TripsFragmentModule(this))
                .injectTo(this)
    }
}
