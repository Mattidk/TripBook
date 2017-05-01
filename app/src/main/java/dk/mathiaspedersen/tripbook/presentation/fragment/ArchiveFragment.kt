package dk.mathiaspedersen.tripbook.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.ButterKnife
import dk.mathiaspedersen.tripbook.R
import dk.mathiaspedersen.tripbook.presentation.injection.ApplicationComponent
import dk.mathiaspedersen.tripbook.presentation.injection.subcomponent.archive.ArchiveFragmentModule
import dk.mathiaspedersen.tripbook.presentation.presenter.ArchivePresenter
import dk.mathiaspedersen.tripbook.presentation.view.ArchiveView
import javax.inject.Inject

class ArchiveFragment : BaseFragment(), ArchiveView {

    @Inject
    lateinit var presenter: ArchivePresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.fragment_archive, container, false)
        ButterKnife.bind(this, view)
        return view
    }

    override fun setTitle() {
        activity.title = getString(R.string.fragment_archive_title)
    }

    override fun injectDependencies(applicationComponent: ApplicationComponent) {
        applicationComponent.plus(ArchiveFragmentModule(this))
                .injectTo(this)
    }

}