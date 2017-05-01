package dk.mathiaspedersen.tripbook.presentation.injection.subcomponent.auto

import dagger.Module
import dagger.Provides
import dk.mathiaspedersen.tripbook.presentation.fragment.AutoFragment
import dk.mathiaspedersen.tripbook.presentation.injection.FragmentModule
import dk.mathiaspedersen.tripbook.presentation.presenter.AutoPresenter
import dk.mathiaspedersen.tripbook.presentation.view.AutoView

@Module
class AutoFragmentModule(fragment: AutoFragment) : FragmentModule(fragment) {

    @Provides
    fun provideAutoView(): AutoView = fragment as AutoView

    @Provides
    fun provideAutoPresenter(view: AutoView) = AutoPresenter(view)

}