package dk.mathiaspedersen.tripbook.presentation.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import dk.mathiaspedersen.tripbook.App
import dk.mathiaspedersen.tripbook.presentation.injection.ApplicationComponent

abstract class BaseActivity : AppCompatActivity() {

    protected abstract val layoutResource: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectDependencies(App.graph)
        setContentView(layoutResource)
    }

    abstract fun injectDependencies(applicationComponent: ApplicationComponent)
}