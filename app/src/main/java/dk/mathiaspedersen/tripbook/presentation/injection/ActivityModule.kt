package dk.mathiaspedersen.tripbook.presentation.injection

import android.content.Context
import android.support.v7.app.AppCompatActivity
import dagger.Module
import dagger.Provides
import dk.mathiaspedersen.tripbook.presentation.helper.DialogHelper
import dk.mathiaspedersen.tripbook.presentation.helper.ViewHelper
import dk.mathiaspedersen.tripbook.presentation.helper.ViewHelperImpl
import dk.mathiaspedersen.tripbook.presentation.injection.scope.ActivityScope

@Module
abstract class ActivityModule(protected val activity: AppCompatActivity) {

    @Provides @ActivityScope
    fun provideActivity(): AppCompatActivity = activity

    @Provides @ActivityScope
    fun provideActiviyContext(): Context = activity

    @Provides @ActivityScope
    fun provideProgressDialogHelper(context: Context): DialogHelper = DialogHelper(context)

    @Provides @ActivityScope
    fun provideHelpview(context: Context, helper: DialogHelper): ViewHelper = ViewHelperImpl(context, helper)
}