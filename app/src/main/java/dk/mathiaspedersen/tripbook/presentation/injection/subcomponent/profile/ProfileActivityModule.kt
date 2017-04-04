package dk.mathiaspedersen.tripbook.presentation.injection.subcomponent.profile

import dagger.Module
import dagger.Provides
import dk.mathiaspedersen.tripbook.domain.interactor.GetUserProfile
import dk.mathiaspedersen.tripbook.domain.interactor.base.Bus
import dk.mathiaspedersen.tripbook.domain.interactor.base.firebase.FirebaseInteractorExecutor
import dk.mathiaspedersen.tripbook.presentation.activity.ProfileActivity
import dk.mathiaspedersen.tripbook.presentation.entity.mapper.UserDetailDataMapper
import dk.mathiaspedersen.tripbook.presentation.injection.ActivityModule
import dk.mathiaspedersen.tripbook.presentation.injection.scope.ActivityScope
import dk.mathiaspedersen.tripbook.presentation.presenter.ProfilePresenter
import dk.mathiaspedersen.tripbook.presentation.view.ProfileView

@Module
class ProfileActivityModule(activity: ProfileActivity) : ActivityModule(activity) {

    @Provides @ActivityScope
    fun provideProfileView(): ProfileView = activity as ProfileView

    @Provides
    fun provideUserDataMapper() = UserDetailDataMapper()

    @Provides @ActivityScope
    fun provideProfilePresenter(view: ProfileView, bus: Bus, getUserInteractor: GetUserProfile, interactorExecutor:
                                FirebaseInteractorExecutor, userDetailDataMapper: UserDetailDataMapper)
                                = ProfilePresenter(view, bus, getUserInteractor, interactorExecutor, userDetailDataMapper)

}