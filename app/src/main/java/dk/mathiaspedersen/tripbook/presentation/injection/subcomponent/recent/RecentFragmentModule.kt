package dk.mathiaspedersen.tripbook.presentation.injection.subcomponent.recent

import dagger.Module
import dagger.Provides
import dk.mathiaspedersen.tripbook.domain.interactor.base.Bus
import dk.mathiaspedersen.tripbook.presentation.fragment.RecentFragment
import dk.mathiaspedersen.tripbook.presentation.injection.FragmentModule
import dk.mathiaspedersen.tripbook.presentation.presenter.RecentPresenter
import dk.mathiaspedersen.tripbook.presentation.view.RecentView

@Module
class RecentFragmentModule(fragment: RecentFragment) : FragmentModule(fragment) {

    @Provides
    fun provideRecentView(): RecentView = fragment as RecentView

    @Provides
    fun provideRecentPresenter(view: RecentView, bus: Bus) = RecentPresenter(view, bus)

}