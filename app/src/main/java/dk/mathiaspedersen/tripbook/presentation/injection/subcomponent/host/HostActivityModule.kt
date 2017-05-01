package dk.mathiaspedersen.tripbook.presentation.injection.subcomponent.host

import dagger.Module
import dagger.Provides
import dk.mathiaspedersen.tripbook.domain.interactor.GetUser
import dk.mathiaspedersen.tripbook.presentation.activity.HostActivity
import dk.mathiaspedersen.tripbook.presentation.entity.mapper.UserDetailDataMapper
import dk.mathiaspedersen.tripbook.presentation.fragment.*
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
    fun provideHostPresenter(view: HostView, getUser: GetUser, mapper: UserDetailDataMapper)
                             = HostPresenter(view, getUser, mapper)

    @Provides @ActivityScope
    fun provideTripsFragment() = TripsFragment()

    @Provides @ActivityScope
    fun provideExpenseFragment() = ExpenseFragment()

    @Provides @ActivityScope
    fun provideArchiveFragment() = ArchiveFragment()

    @Provides @ActivityScope
    fun provideAutoFragment() = AutoFragment()

    @Provides @ActivityScope
    fun provideReportFragment() = ReportFragment()

    @Provides @ActivityScope
    fun provideInviteFragment() = InviteFragment()
}