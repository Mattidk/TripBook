package dk.mathiaspedersen.tripbook.presentation.activity

import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import butterknife.BindView
import butterknife.ButterKnife
import dk.mathiaspedersen.tripbook.R
import dk.mathiaspedersen.tripbook.presentation.injection.ApplicationComponent
import dk.mathiaspedersen.tripbook.presentation.injection.subcomponent.search.SearchActivityModule
import dk.mathiaspedersen.tripbook.presentation.presenter.SearchPresenter
import dk.mathiaspedersen.tripbook.presentation.view.SearchView
import javax.inject.Inject

class SearchActivity : BaseActivity(), SearchView {

    @BindView(R.id.toolbar)
    lateinit var toolbar: Toolbar

    @Inject
    lateinit var presenter: SearchPresenter

    override val layoutResource: Int = R.layout.activity_search

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ButterKnife.bind(this)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    override fun onResume() {
        super.onResume()
        presenter.onResume()
    }

    override fun onPause() {
        super.onPause()
        presenter.onPause()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun injectDependencies(applicationComponent: ApplicationComponent) {
        applicationComponent.plus(SearchActivityModule(this))
                .injectTo(this)
    }

}