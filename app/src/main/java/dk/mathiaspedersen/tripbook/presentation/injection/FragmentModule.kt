package dk.mathiaspedersen.tripbook.presentation.injection

import android.app.Fragment
import android.content.Context
import dagger.Module
import dagger.Provides
import dk.mathiaspedersen.tripbook.presentation.helper.DialogHelper
import dk.mathiaspedersen.tripbook.presentation.helper.ViewHelper
import dk.mathiaspedersen.tripbook.presentation.helper.ViewHelperImpl
import dk.mathiaspedersen.tripbook.presentation.injection.scope.ActivityScope

@Module
abstract class FragmentModule(protected val fragment: Fragment) {

    @Provides @ActivityScope
    fun provideFragment(): Fragment = fragment

    @Provides @ActivityScope
    fun provideFragmentContext(): Context = fragment.activity

    @Provides
    fun provideProgressDialogHelper(context: Context): DialogHelper = DialogHelper(context)

    @Provides
    fun provideHelpview(context: Context, helper: DialogHelper): ViewHelper = ViewHelperImpl(context, helper)
}