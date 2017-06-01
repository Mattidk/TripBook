package dk.mathiaspedersen.tripbook.presentation.injection.subcomponent.expensedetail

import dagger.Module
import dagger.Provides
import dk.mathiaspedersen.tripbook.presentation.activity.ExpenseDetailActivity
import dk.mathiaspedersen.tripbook.presentation.injection.ActivityModule
import dk.mathiaspedersen.tripbook.presentation.injection.scope.ActivityScope
import dk.mathiaspedersen.tripbook.presentation.presenter.ExpenseDetailPresenter
import dk.mathiaspedersen.tripbook.presentation.view.ExpenseDetailView

@Module
class ExpenseDetailActivityModule(activity: ExpenseDetailActivity) : ActivityModule(activity) {

    @Provides @ActivityScope
    fun provideExpenseDetailView(): ExpenseDetailView = activity as ExpenseDetailView

    @Provides @ActivityScope
    fun provideExpenseDetailPresenter(view: ExpenseDetailView) = ExpenseDetailPresenter(view)
}