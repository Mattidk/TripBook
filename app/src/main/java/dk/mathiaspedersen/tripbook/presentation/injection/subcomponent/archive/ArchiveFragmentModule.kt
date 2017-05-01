package dk.mathiaspedersen.tripbook.presentation.injection.subcomponent.archive

import dagger.Module
import dagger.Provides
import dk.mathiaspedersen.tripbook.presentation.fragment.ArchiveFragment
import dk.mathiaspedersen.tripbook.presentation.injection.FragmentModule
import dk.mathiaspedersen.tripbook.presentation.presenter.ArchivePresenter
import dk.mathiaspedersen.tripbook.presentation.view.ArchiveView

@Module
class ArchiveFragmentModule(fragment: ArchiveFragment) : FragmentModule(fragment) {

    @Provides
    fun provideArchiveView(): ArchiveView = fragment as ArchiveView

    @Provides
    fun provideArchivePresenter(view: ArchiveView) = ArchivePresenter(view)

}