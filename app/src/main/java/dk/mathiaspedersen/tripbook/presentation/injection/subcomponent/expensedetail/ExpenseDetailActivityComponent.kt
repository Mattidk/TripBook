package dk.mathiaspedersen.tripbook.presentation.injection.subcomponent.expensedetail

import dagger.Subcomponent
import dk.mathiaspedersen.tripbook.presentation.activity.ExpenseDetailActivity
import dk.mathiaspedersen.tripbook.presentation.injection.scope.ActivityScope

@ActivityScope
@Subcomponent(modules = arrayOf(
        ExpenseDetailActivityModule::class
))
interface ExpenseDetailActivityComponent {
    fun injectTo(activity: ExpenseDetailActivity)
}