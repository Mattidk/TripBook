package dk.mathiaspedersen.tripbook.presentation.activity

import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.View
import butterknife.BindView
import butterknife.ButterKnife
import dk.mathiaspedersen.tripbook.R
import dk.mathiaspedersen.tripbook.presentation.injection.ApplicationComponent
import dk.mathiaspedersen.tripbook.presentation.injection.subcomponent.expensedetail.ExpenseDetailActivityModule
import dk.mathiaspedersen.tripbook.presentation.presenter.ExpenseDetailPresenter
import dk.mathiaspedersen.tripbook.presentation.view.ExpenseDetailView
import javax.inject.Inject


class ExpenseDetailActivity : BaseActivity(), ExpenseDetailView {

    override val layoutResource: Int = R.layout.activity_expense_detail

    @BindView(R.id.toolbar)
    lateinit var toolbar: Toolbar

    @Inject
    lateinit var presenter: ExpenseDetailPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ButterKnife.bind(this)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
    }

    override fun injectDependencies(applicationComponent: ApplicationComponent) {
        applicationComponent.plus(ExpenseDetailActivityModule(this))
                .injectTo(this)
    }
}
