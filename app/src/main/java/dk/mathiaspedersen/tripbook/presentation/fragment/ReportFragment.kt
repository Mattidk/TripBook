package dk.mathiaspedersen.tripbook.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.ButterKnife
import dk.mathiaspedersen.tripbook.R
import dk.mathiaspedersen.tripbook.presentation.injection.ApplicationComponent
import dk.mathiaspedersen.tripbook.presentation.injection.subcomponent.report.ReportFragmentModule
import dk.mathiaspedersen.tripbook.presentation.presenter.ReportPresenter
import dk.mathiaspedersen.tripbook.presentation.view.ReportView
import javax.inject.Inject

class ReportFragment : BaseFragment(), ReportView {

    @Inject
    lateinit var presenter: ReportPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.fragment_report, container, false)
        ButterKnife.bind(this, view)
        return view
    }

    override fun setTitle() {
        activity.title = getString(R.string.fragment_report_title)
    }

    override fun injectDependencies(applicationComponent: ApplicationComponent) {
        applicationComponent.plus(ReportFragmentModule(this))
                .injectTo(this)
    }

}