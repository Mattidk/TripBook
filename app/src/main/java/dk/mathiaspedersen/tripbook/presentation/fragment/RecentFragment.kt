package dk.mathiaspedersen.tripbook.presentation.fragment


import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.*
import android.widget.ProgressBar
import butterknife.BindView
import butterknife.ButterKnife
import dk.mathiaspedersen.tripbook.R
import dk.mathiaspedersen.tripbook.presentation.custom.RecentAdapter
import dk.mathiaspedersen.tripbook.presentation.custom.RecentItemAnimator
import dk.mathiaspedersen.tripbook.presentation.entity.TripDetail
import dk.mathiaspedersen.tripbook.presentation.helper.AppSettings
import dk.mathiaspedersen.tripbook.presentation.injection.ApplicationComponent
import dk.mathiaspedersen.tripbook.presentation.injection.subcomponent.recent.RecentFragmentModule
import dk.mathiaspedersen.tripbook.presentation.presenter.RecentPresenter
import dk.mathiaspedersen.tripbook.presentation.view.RecentView
import java.util.*
import javax.inject.Inject

class RecentFragment : BaseFragment(), RecentView {

    @Inject lateinit var presenter: RecentPresenter

    @Inject
    lateinit var settings: AppSettings

    @Inject
    lateinit var adapter: RecentAdapter

    @BindView(R.id.loading_spinner)
    lateinit var spinner: ProgressBar

    @BindView(R.id.recent_recyclerview)
    lateinit var recyclerView: RecyclerView

    @BindView(R.id.refresh_container)
    lateinit var swipeRefreshLayout: SwipeRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.fragment_recent, container, false)
        ButterKnife.bind(this, view)
        setScrollFlags()
        setupViews()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        swipeRefreshLayout.setOnRefreshListener {
            fetchTrips()
        }
    }

    override fun populateRecyclerView(trips: ArrayList<TripDetail>) {
        spinner.visibility = View.GONE
        recyclerView.itemAnimator = RecentItemAnimator()
        swipeRefreshLayout.isRefreshing = false
        adapter.refresh(trips)
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
            R.id.menu_refresh -> {
                if (!swipeRefreshLayout.isRefreshing) {
                    swipeRefreshLayout.isRefreshing = true
                }
                fetchTrips()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun setScrollFlags() {
        val toolbar = activity.findViewById(R.id.toolbar) as Toolbar
        val params = toolbar.layoutParams as AppBarLayout.LayoutParams
        params.scrollFlags = AppBarLayout.LayoutParams.SCROLL_FLAG_SNAP
    }

    override fun onResume() {
        super.onResume()
        swipeRefreshLayout.isRefreshing = false
        presenter.onResume()
    }

    override fun onPause() {
        super.onPause()
        presenter.onPause()
    }

    fun fetchTrips() {
        if (!swipeRefreshLayout.isRefreshing) {
            swipeRefreshLayout.isRefreshing = true
        }
        adapter.refresh(arrayListOf<TripDetail>())
        presenter.getRecentTrips()
    }

    fun setupViews() {

        activity.title = "Recent"

        recyclerView.setHasFixedSize(true)
        val mLayoutManager = LinearLayoutManager(activity)
        recyclerView.layoutManager = mLayoutManager as RecyclerView.LayoutManager?
        recyclerView.adapter = adapter

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
        applicationComponent.plus(RecentFragmentModule(this))
                .injectTo(this)
    }
}
