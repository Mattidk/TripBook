package dk.mathiaspedersen.tripbook.presentation.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dk.mathiaspedersen.tripbook.R
import dk.mathiaspedersen.tripbook.domain.entity.Trip
import dk.mathiaspedersen.tripbook.presentation.injection.ApplicationComponent
import dk.mathiaspedersen.tripbook.presentation.injection.subcomponent.recent.RecentFragmentModule
import dk.mathiaspedersen.tripbook.presentation.presenter.RecentPresenter
import dk.mathiaspedersen.tripbook.presentation.view.RecentView
import javax.inject.Inject

class RecentFragment : BaseFragment(), RecentView {

    @Inject lateinit var presenter: RecentPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_history, container, false)
    }

    override fun example(response: List<Trip>) {
        // Temporarily empty
    }

    override fun onResume() {
        super.onResume()
        presenter.onResume()
        activity.title = "History"
    }

    override fun onPause() {
        super.onPause()
        presenter.onPause()
    }

    override fun injectDependencies(applicationComponent: ApplicationComponent) {
        applicationComponent.plus(RecentFragmentModule(this))
                .injectTo(this)
    }
}
