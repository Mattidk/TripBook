package dk.mathiaspedersen.tripbook.presentation.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import dk.mathiaspedersen.tripbook.App
import dk.mathiaspedersen.tripbook.presentation.helper.AppSettings
import dk.mathiaspedersen.tripbook.presentation.injection.ApplicationComponent
import javax.inject.Inject

abstract class BaseActivity : AppCompatActivity() {

    @Inject
    lateinit var baseSettings: AppSettings

    protected abstract val layoutResource: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependencies(App.graph)
        setTheme(baseSettings.getSelectedTheme())
        super.onCreate(savedInstanceState)
        setContentView(layoutResource)
    }

    abstract fun injectDependencies(applicationComponent: ApplicationComponent)
}