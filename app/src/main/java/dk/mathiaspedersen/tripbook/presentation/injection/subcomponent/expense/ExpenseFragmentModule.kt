package dk.mathiaspedersen.tripbook.presentation.injection.subcomponent.trips

import dagger.Module
import dagger.Provides
import dk.mathiaspedersen.tripbook.domain.interactor.base.Bus
import dk.mathiaspedersen.tripbook.presentation.fragment.ExpenseFragment
import dk.mathiaspedersen.tripbook.presentation.injection.FragmentModule
import dk.mathiaspedersen.tripbook.presentation.presenter.ExpensePresenter
import dk.mathiaspedersen.tripbook.presentation.view.ExpenseView

@Module
class ExpenseFragmentModule(fragment: ExpenseFragment) : FragmentModule(fragment) {

    @Provides
    fun provideExpenseView(): ExpenseView = fragment as ExpenseView

    @Provides
    fun provideExpensePresenter(view: ExpenseView, bus: Bus) = ExpensePresenter(view, bus)

}