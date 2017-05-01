package dk.mathiaspedersen.tripbook.presentation.injection.subcomponent.invite

import dagger.Module
import dagger.Provides
import dk.mathiaspedersen.tripbook.presentation.fragment.InviteFragment
import dk.mathiaspedersen.tripbook.presentation.injection.FragmentModule
import dk.mathiaspedersen.tripbook.presentation.presenter.InvitePresenter
import dk.mathiaspedersen.tripbook.presentation.view.InviteView

@Module
class InviteFragmentModule(fragment: InviteFragment) : FragmentModule(fragment) {

    @Provides
    fun provideInviteView(): InviteView = fragment as InviteView

    @Provides
    fun provideInvitePresenter(view: InviteView) = InvitePresenter(view)

}