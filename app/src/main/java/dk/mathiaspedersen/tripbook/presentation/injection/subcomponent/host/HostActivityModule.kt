package dk.mathiaspedersen.tripbook.presentation.injection.subcomponent.host

import dagger.Module
import dagger.Provides
import dk.mathiaspedersen.tripbook.domain.interactor.GetUnclassifiedTrips
import dk.mathiaspedersen.tripbook.domain.interactor.GetUserProfile
import dk.mathiaspedersen.tripbook.domain.interactor.base.Bus
import dk.mathiaspedersen.tripbook.domain.interactor.base.firebase.FirebaseInteractorExecutor
import dk.mathiaspedersen.tripbook.presentation.activity.HostActivity
import dk.mathiaspedersen.tripbook.presentation.entity.mapper.UserDetailDataMapper
import dk.mathiaspedersen.tripbook.presentation.fragment.TripsFragment
import dk.mathiaspedersen.tripbook.presentation.injection.ActivityModule
import dk.mathiaspedersen.tripbook.presentation.injection.scope.ActivityScope
import dk.mathiaspedersen.tripbook.presentation.presenter.HostPresenter
import dk.mathiaspedersen.tripbook.presentation.view.HostView

@Module
class HostActivityModule(activity: HostActivity) : ActivityModule(activity) {

    @Provides @ActivityScope
    fun provideHostView(): HostView = activity as HostView


    @Provides
    fun provideUserDataMapper() = UserDetailDataMapper()

    @Provides @ActivityScope
    fun provideHostPresenter(view: HostView, bus: Bus, getUserInteractor: GetUserProfile,
                             getUnclassifiedTrips: GetUnclassifiedTrips, interactorExecutor:
                             FirebaseInteractorExecutor, userDetailDataMapper: UserDetailDataMapper)
                             = HostPresenter(view, bus, getUserInteractor, getUnclassifiedTrips,
                             interactorExecutor, userDetailDataMapper)

    @Provides @ActivityScope
    fun provideTripsFragment() = TripsFragment()
}