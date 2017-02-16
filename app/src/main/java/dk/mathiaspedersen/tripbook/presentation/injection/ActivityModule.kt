package dk.mathiaspedersen.tripbook.presentation.injection

import android.content.Context
import android.support.v7.app.AppCompatActivity
import dagger.Module
import dagger.Provides
import dk.mathiaspedersen.tripbook.presentation.injection.scope.ActivityScope

@Module
abstract class ActivityModule(protected val activity: AppCompatActivity) {

    @Provides @ActivityScope
    fun provideActivity(): AppCompatActivity = activity

    @Provides @ActivityScope
    fun provideActiviyContext(): Context = activity.baseContext
}