package dk.mathiaspedersen.tripbook.presentation.activity

import android.app.Fragment
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import dk.mathiaspedersen.tripbook.App
import dk.mathiaspedersen.tripbook.R
import dk.mathiaspedersen.tripbook.presentation.injection.ApplicationComponent
import org.jetbrains.anko.find

abstract class BaseActivity : AppCompatActivity() {

    protected abstract val layoutResource: Int

    val toolbar: Toolbar by lazy { find<Toolbar>(R.id.toolbar) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectDependencies(App.graph)
        setContentView(layoutResource)
        setSupportActionBar(toolbar)
    }

    abstract fun injectDependencies(applicationComponent: ApplicationComponent)

    fun navigateTo(fragment: Fragment) {
        val fragmentManager = fragmentManager
        fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit()
    }
}