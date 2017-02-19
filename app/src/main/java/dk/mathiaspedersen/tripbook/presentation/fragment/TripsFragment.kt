package dk.mathiaspedersen.tripbook.presentation.fragment


import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import dk.mathiaspedersen.tripbook.R
import dk.mathiaspedersen.tripbook.presentation.injection.ApplicationComponent
import dk.mathiaspedersen.tripbook.presentation.injection.subcomponent.trips.TripsFragmentModule
import dk.mathiaspedersen.tripbook.presentation.presenter.TripsPresenter
import dk.mathiaspedersen.tripbook.presentation.view.TripsView
import org.jetbrains.anko.find
import javax.inject.Inject

class TripsFragment : BaseFragment(), TripsView {

    override val layoutResource: Int = R.layout.fragment_trips

    val spinner by lazy { find<ProgressBar>(R.id.loading_spinner) }
    val tv by lazy { find<TextView>(R.id.example_tv) }

    @Inject
    lateinit var presenter: TripsPresenter

    override fun example(example: String) {
        spinner.visibility = View.GONE
        tv.visibility = View.VISIBLE
        tv.text = example
    }

    override fun onResume() {
        super.onResume()
        spinner.visibility = View.VISIBLE
        tv.visibility = View.GONE
        presenter.onResume()
    }

    override fun onPause() {
        super.onPause()
        presenter.onPause()
    }

    override fun injectDependencies(applicationComponent: ApplicationComponent) {
        applicationComponent.plus(TripsFragmentModule(this))
                .injectTo(this)
    }
}
