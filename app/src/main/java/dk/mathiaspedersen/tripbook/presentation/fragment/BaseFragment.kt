package dk.mathiaspedersen.tripbook.presentation.fragment

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dk.mathiaspedersen.tripbook.App
import dk.mathiaspedersen.tripbook.presentation.injection.ApplicationComponent

abstract class BaseFragment : Fragment() {

    protected abstract val layoutResource: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectDependencies(App.graph)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(layoutResource, container, false)
    }

    abstract fun injectDependencies(applicationComponent: ApplicationComponent)
}