package dk.mathiaspedersen.tripbook.presentation.fragment

import android.app.Fragment
import android.os.Bundle
import dk.mathiaspedersen.tripbook.App
import dk.mathiaspedersen.tripbook.presentation.injection.ApplicationComponent

abstract class BaseFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectDependencies(App.graph)
    }

    abstract fun injectDependencies(applicationComponent: ApplicationComponent)
}