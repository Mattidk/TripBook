package dk.mathiaspedersen.tripbook.presentation.injection

import dagger.Component
import dk.mathiaspedersen.tripbook.presentation.injection.subcomponent.main.MainActivityComponent
import dk.mathiaspedersen.tripbook.presentation.injection.subcomponent.main.MainActivityModule
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(
        ApplicationModule::class,
        RepositoryModule::class,
        DomainModule::class,
        DataModule::class
))
interface ApplicationComponent {
    fun plus(module: MainActivityModule): MainActivityComponent
}