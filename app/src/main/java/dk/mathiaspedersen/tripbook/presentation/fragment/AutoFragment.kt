package dk.mathiaspedersen.tripbook.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.ButterKnife
import dk.mathiaspedersen.tripbook.R
import dk.mathiaspedersen.tripbook.presentation.injection.ApplicationComponent
import dk.mathiaspedersen.tripbook.presentation.injection.subcomponent.auto.AutoFragmentModule
import dk.mathiaspedersen.tripbook.presentation.presenter.AutoPresenter
import dk.mathiaspedersen.tripbook.presentation.view.AutoView
import javax.inject.Inject

class AutoFragment : BaseFragment(), AutoView {

    @Inject
    lateinit var presenter: AutoPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.fragment_auto, container, false)
        ButterKnife.bind(this, view)
        return view
    }

    override fun setTitle() {
        activity.title = getString(R.string.fragment_auto_title)
    }

    override fun injectDependencies(applicationComponent: ApplicationComponent) {
        applicationComponent.plus(AutoFragmentModule(this))
                .injectTo(this)
    }

}