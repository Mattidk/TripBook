package dk.mathiaspedersen.tripbook.presentation.injection.subcomponent.archive

import dagger.Module
import dagger.Provides
import dk.mathiaspedersen.tripbook.domain.interactor.base.Bus
import dk.mathiaspedersen.tripbook.presentation.fragment.ArchiveFragment
import dk.mathiaspedersen.tripbook.presentation.fragment.AutoFragment
import dk.mathiaspedersen.tripbook.presentation.injection.FragmentModule
import dk.mathiaspedersen.tripbook.presentation.presenter.ArchivePresenter
import dk.mathiaspedersen.tripbook.presentation.presenter.AutoPresenter
import dk.mathiaspedersen.tripbook.presentation.view.ArchiveView
import dk.mathiaspedersen.tripbook.presentation.view.AutoView

@Module
class ArchiveFragmentModule(fragment: ArchiveFragment) : FragmentModule(fragment) {

    @Provides
    fun provideArchiveView(): ArchiveView = fragment as ArchiveView

    @Provides
    fun provideArchivePresenter(view: ArchiveView, bus: Bus) = ArchivePresenter(view, bus)

}