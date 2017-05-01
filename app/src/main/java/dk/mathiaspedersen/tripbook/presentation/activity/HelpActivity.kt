package dk.mathiaspedersen.tripbook.presentation.activity

import android.os.Bundle
import android.support.v7.widget.Toolbar
import butterknife.BindView
import butterknife.ButterKnife
import dk.mathiaspedersen.tripbook.R
import dk.mathiaspedersen.tripbook.presentation.injection.ApplicationComponent
import dk.mathiaspedersen.tripbook.presentation.injection.subcomponent.host.HelpActivityModule
import dk.mathiaspedersen.tripbook.presentation.presenter.HelpPresenter
import dk.mathiaspedersen.tripbook.presentation.view.HelpView
import javax.inject.Inject

class HelpActivity : BaseActivity(), HelpView {

    override val layoutResource: Int = R.layout.activity_help

    @BindView(R.id.toolbar)
    lateinit var toolbar: Toolbar

    @Inject
    lateinit var presenter: HelpPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ButterKnife.bind(this)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun injectDependencies(applicationComponent: ApplicationComponent) {
        applicationComponent.plus(HelpActivityModule(this))
                .injectTo(this)
    }
}
