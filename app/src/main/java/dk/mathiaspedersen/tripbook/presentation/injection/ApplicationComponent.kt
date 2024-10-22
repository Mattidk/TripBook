package dk.mathiaspedersen.tripbook.presentation.injection

import dagger.Component
import dk.mathiaspedersen.tripbook.presentation.injection.subcomponent.about.AboutActivityComponent
import dk.mathiaspedersen.tripbook.presentation.injection.subcomponent.about.AboutActivityModule
import dk.mathiaspedersen.tripbook.presentation.injection.subcomponent.archive.ArchiveFragmentComponent
import dk.mathiaspedersen.tripbook.presentation.injection.subcomponent.archive.ArchiveFragmentModule
import dk.mathiaspedersen.tripbook.presentation.injection.subcomponent.auto.AutoFragmentComponent
import dk.mathiaspedersen.tripbook.presentation.injection.subcomponent.auto.AutoFragmentModule
import dk.mathiaspedersen.tripbook.presentation.injection.subcomponent.detail.DetailActivityComponent
import dk.mathiaspedersen.tripbook.presentation.injection.subcomponent.detail.DetailActivityModule
import dk.mathiaspedersen.tripbook.presentation.injection.subcomponent.expense.ExpenseFragmentComponent
import dk.mathiaspedersen.tripbook.presentation.injection.subcomponent.expense.ExpenseFragmentModule
import dk.mathiaspedersen.tripbook.presentation.injection.subcomponent.expensedetail.ExpenseDetailActivityComponent
import dk.mathiaspedersen.tripbook.presentation.injection.subcomponent.expensedetail.ExpenseDetailActivityModule
import dk.mathiaspedersen.tripbook.presentation.injection.subcomponent.help.HelpActivityComponent
import dk.mathiaspedersen.tripbook.presentation.injection.subcomponent.host.HelpActivityModule
import dk.mathiaspedersen.tripbook.presentation.injection.subcomponent.host.HostActivityComponent
import dk.mathiaspedersen.tripbook.presentation.injection.subcomponent.host.HostActivityModule
import dk.mathiaspedersen.tripbook.presentation.injection.subcomponent.hostSettings.HostSettingsActivityComponent
import dk.mathiaspedersen.tripbook.presentation.injection.subcomponent.hostSettings.HostSettingsActivityModule
import dk.mathiaspedersen.tripbook.presentation.injection.subcomponent.intro.IntroActivityComponent
import dk.mathiaspedersen.tripbook.presentation.injection.subcomponent.intro.IntroActivityModule
import dk.mathiaspedersen.tripbook.presentation.injection.subcomponent.invite.InviteFragmentComponent
import dk.mathiaspedersen.tripbook.presentation.injection.subcomponent.invite.InviteFragmentModule
import dk.mathiaspedersen.tripbook.presentation.injection.subcomponent.login.LoginActivityComponent
import dk.mathiaspedersen.tripbook.presentation.injection.subcomponent.login.LoginActivityModule
import dk.mathiaspedersen.tripbook.presentation.injection.subcomponent.profile.ProfileActivityComponent
import dk.mathiaspedersen.tripbook.presentation.injection.subcomponent.profile.ProfileActivityModule
import dk.mathiaspedersen.tripbook.presentation.injection.subcomponent.report.ReportFragmentComponent
import dk.mathiaspedersen.tripbook.presentation.injection.subcomponent.report.ReportFragmentModule
import dk.mathiaspedersen.tripbook.presentation.injection.subcomponent.settings.SettingsFragmentComponent
import dk.mathiaspedersen.tripbook.presentation.injection.subcomponent.settings.SettingsFragmentModule
import dk.mathiaspedersen.tripbook.presentation.injection.subcomponent.trips.TripsFragmentComponent
import dk.mathiaspedersen.tripbook.presentation.injection.subcomponent.trips.TripsFragmentModule
import dk.mathiaspedersen.tripbook.presentation.injection.subcomponent.vehicledetail.VehicleDetailActivityComponent
import dk.mathiaspedersen.tripbook.presentation.injection.subcomponent.vehicledetail.VehicleDetailActivityModule
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(
        ApplicationModule::class,
        RepositoryModule::class,
        DomainModule::class,
        DataModule::class,
        ManagerModule::class,
        DataSourceModule::class
))
interface ApplicationComponent {

    fun plus(module: LoginActivityModule): LoginActivityComponent
    fun plus(module: HostActivityModule): HostActivityComponent
    fun plus(module: TripsFragmentModule): TripsFragmentComponent
    fun plus(module: ExpenseFragmentModule): ExpenseFragmentComponent
    fun plus(module: ArchiveFragmentModule): ArchiveFragmentComponent
    fun plus(module: InviteFragmentModule): InviteFragmentComponent
    fun plus(module: ReportFragmentModule): ReportFragmentComponent
    fun plus(module: AutoFragmentModule): AutoFragmentComponent
    fun plus(module: DetailActivityModule): DetailActivityComponent
    fun plus(module: HostSettingsActivityModule): HostSettingsActivityComponent
    fun plus(module: SettingsFragmentModule): SettingsFragmentComponent
    fun plus(module: AboutActivityModule): AboutActivityComponent
    fun plus(module: ProfileActivityModule): ProfileActivityComponent
    fun plus(module: IntroActivityModule): IntroActivityComponent
    fun plus(module: HelpActivityModule): HelpActivityComponent
    fun plus(module: VehicleDetailActivityModule): VehicleDetailActivityComponent
    fun plus(module: ExpenseDetailActivityModule): ExpenseDetailActivityComponent
}