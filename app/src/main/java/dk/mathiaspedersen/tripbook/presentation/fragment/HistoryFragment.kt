package dk.mathiaspedersen.tripbook.presentation.fragment


import dk.mathiaspedersen.tripbook.R
import dk.mathiaspedersen.tripbook.presentation.injection.ApplicationComponent
import dk.mathiaspedersen.tripbook.presentation.injection.subcomponent.history.HistoryFragmentModule
import dk.mathiaspedersen.tripbook.presentation.presenter.HistoryPresenter
import dk.mathiaspedersen.tripbook.presentation.view.HistoryView
import javax.inject.Inject

class HistoryFragment : BaseFragment(), HistoryView {

    override val layoutResource: Int = R.layout.fragment_history

    @Inject
    lateinit var presenter: HistoryPresenter

    override fun example(example: String) {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onResume() {
        super.onResume()
        presenter.onResume()
    }

    override fun onPause() {
        super.onPause()
        presenter.onPause()
    }

    override fun injectDependencies(applicationComponent: ApplicationComponent) {
        applicationComponent.plus(HistoryFragmentModule(this))
                .injectTo(this)
    }

    companion object {
        fun newInstance(): HistoryFragment {
            return HistoryFragment()
        }
    }

}
