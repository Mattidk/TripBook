package dk.mathiaspedersen.tripbook.presentation.activity

import android.app.SearchManager
import android.content.ComponentName
import android.content.Context
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.ImageView
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import dk.mathiaspedersen.tripbook.R
import dk.mathiaspedersen.tripbook.presentation.injection.ApplicationComponent
import dk.mathiaspedersen.tripbook.presentation.injection.subcomponent.search.SearchActivityModule
import dk.mathiaspedersen.tripbook.presentation.presenter.SearchPresenter
import dk.mathiaspedersen.tripbook.presentation.view.SearchView
import javax.inject.Inject

class SearchActivity : BaseActivity(), SearchView {

    @BindView(R.id.navigate_up)
    lateinit var back: ImageView

    @BindView(R.id.searchbar)
    lateinit var searchbar: android.support.v7.widget.SearchView

    @BindView(R.id.toolbar)
    lateinit var toolbar: Toolbar

    @Inject
    lateinit var presenter: SearchPresenter

    override val layoutResource: Int = R.layout.activity_search

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ButterKnife.bind(this)
        setSupportActionBar(toolbar)
        initializeSearch()
    }

    @OnClick(R.id.navigate_up)
    fun back(view: View){
        finish()
    }

    override fun onBackPressed() {
        finish()
    }

    private fun initializeSearch() {
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val componentName = ComponentName(this, SearchActivity::class.java)
        searchbar.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchbar.onActionViewExpanded()
    }

    override fun injectDependencies(applicationComponent: ApplicationComponent) {
        applicationComponent.plus(SearchActivityModule(this))
                .injectTo(this)
    }

}