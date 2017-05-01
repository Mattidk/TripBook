package dk.mathiaspedersen.tripbook.presentation.injection.subcomponent.report

import dagger.Module
import dagger.Provides
import dk.mathiaspedersen.tripbook.presentation.fragment.ReportFragment
import dk.mathiaspedersen.tripbook.presentation.injection.FragmentModule
import dk.mathiaspedersen.tripbook.presentation.presenter.ReportPresenter
import dk.mathiaspedersen.tripbook.presentation.view.ReportView

@Module
class ReportFragmentModule(fragment: ReportFragment) : FragmentModule(fragment) {

    @Provides
    fun provideReportView(): ReportView = fragment as ReportView

    @Provides
    fun provideReportPresenter(view: ReportView) = ReportPresenter(view)

}