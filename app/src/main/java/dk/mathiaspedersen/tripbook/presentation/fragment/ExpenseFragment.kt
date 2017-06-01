package dk.mathiaspedersen.tripbook.presentation.fragment

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.ButterKnife
import dk.mathiaspedersen.tripbook.R
import dk.mathiaspedersen.tripbook.presentation.activity.ExpenseDetailActivity
import dk.mathiaspedersen.tripbook.presentation.injection.ApplicationComponent
import dk.mathiaspedersen.tripbook.presentation.injection.subcomponent.expense.ExpenseFragmentModule
import dk.mathiaspedersen.tripbook.presentation.presenter.ExpensePresenter
import dk.mathiaspedersen.tripbook.presentation.view.ExpenseView
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity
import javax.inject.Inject

class ExpenseFragment : BaseFragment(), ExpenseView {

    @Inject
    lateinit var presenter: ExpensePresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.fragment_expense, container, false)
        ButterKnife.bind(this, view)
        configureFloatingActionButton()
        return view
    }

    fun configureFloatingActionButton() {
        val fab = activity.find<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener { view ->
            activity.startActivity<ExpenseDetailActivity>()
        }
    }

    override fun setTitle() {
        activity.title = getString(R.string.fragment_expenses_title)
    }

    override fun injectDependencies(applicationComponent: ApplicationComponent) {
        applicationComponent.plus(ExpenseFragmentModule(this))
                .injectTo(this)
    }
}