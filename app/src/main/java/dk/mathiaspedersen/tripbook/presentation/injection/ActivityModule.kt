package dk.mathiaspedersen.tripbook.presentation.injection

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import android.support.v7.app.AppCompatActivity
import dagger.Module
import dagger.Provides
import dk.mathiaspedersen.tripbook.presentation.helper.*
import dk.mathiaspedersen.tripbook.presentation.injection.qualifier.AppQualifier
import dk.mathiaspedersen.tripbook.presentation.injection.scope.ActivityScope
import javax.inject.Singleton

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