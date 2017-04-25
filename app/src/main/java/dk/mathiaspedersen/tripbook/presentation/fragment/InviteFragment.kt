package dk.mathiaspedersen.tripbook.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.ButterKnife
import dk.mathiaspedersen.tripbook.R
import dk.mathiaspedersen.tripbook.presentation.injection.ApplicationComponent
import dk.mathiaspedersen.tripbook.presentation.injection.subcomponent.invite.InviteFragmentModule
import dk.mathiaspedersen.tripbook.presentation.presenter.InvitePresenter
import dk.mathiaspedersen.tripbook.presentation.view.InviteView
import javax.inject.Inject

class InviteFragment : BaseFragment(), InviteView {

    @Inject
    lateinit var presenter: InvitePresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.fragment_invite, container, false)
        ButterKnife.bind(this, view)
        return view
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
        applicationComponent.plus(InviteFragmentModule(this))
                .injectTo(this)
    }

}