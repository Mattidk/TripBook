package dk.mathiaspedersen.tripbook.presentation.injection

import android.app.Fragment
import dagger.Module
import dagger.Provides
import dk.mathiaspedersen.tripbook.presentation.injection.scope.ActivityScope

@Module
abstract class FragmentModule(protected val fragment: Fragment) {

    @Provides @ActivityScope
    fun provideFragment(): Fragment = fragment

}