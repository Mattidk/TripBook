package dk.mathiaspedersen.tripbook.presentation

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import dk.mathiaspedersen.tripbook.App
import dk.mathiaspedersen.tripbook.R
import dk.mathiaspedersen.tripbook.presentation.injection.ApplicationComponent
import org.jetbrains.anko.find

abstract class BaseActivity : AppCompatActivity() {

    companion object {
        val IMAGE_TRANSITION_NAME = "activity_image_transition"
    }

    protected abstract val layoutResource: Int
    val toolbar: Toolbar by lazy { find<Toolbar>(R.id.toolbar) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectDependencies(App.graph)
        setContentView(layoutResource)
        setSupportActionBar(toolbar)
    }

    abstract fun injectDependencies(applicationComponent: ApplicationComponent)
}