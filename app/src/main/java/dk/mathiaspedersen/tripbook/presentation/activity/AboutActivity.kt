package dk.mathiaspedersen.tripbook.presentation.activity

import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.design.widget.CollapsingToolbarLayout
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.widget.RelativeLayout
import butterknife.BindView
import butterknife.ButterKnife
import dk.mathiaspedersen.tripbook.R
import dk.mathiaspedersen.tripbook.presentation.injection.ApplicationComponent
import dk.mathiaspedersen.tripbook.presentation.injection.subcomponent.about.AboutActivityModule
import dk.mathiaspedersen.tripbook.presentation.presenter.AboutPresenter
import dk.mathiaspedersen.tripbook.presentation.view.AboutView
import javax.inject.Inject


class AboutActivity : BaseActivity(), AboutView {

    override val layoutResource: Int = R.layout.activity_about

    @BindView(R.id.header_content)
    lateinit var header: RelativeLayout

    @BindView(R.id.app_bar)
    lateinit var appBarLayout: AppBarLayout

    @BindView(R.id.toolbar_layout)
    lateinit var collapsingToolbarLayout: CollapsingToolbarLayout

    @BindView(R.id.toolbar)
    lateinit var toolbar: Toolbar

    @Inject
    lateinit var presenter: AboutPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ButterKnife.bind(this)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        appBarLayout.addOnOffsetChangedListener(object : AppBarLayout.OnOffsetChangedListener {
            internal var isShow = false
            internal var scrollRange = -1

            override fun onOffsetChanged(appBarLayout: AppBarLayout, verticalOffset: Int) {

                val percentage = 1.0f - Math.abs(verticalOffset).toFloat() / appBarLayout.totalScrollRange
                header.alpha = percentage

                if (scrollRange == -1) {
                    scrollRange = appBarLayout.totalScrollRange
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbarLayout.title = applicationContext.getString(R.string.activity_about_title)
                    isShow = true
                } else if (isShow) {
                    collapsingToolbarLayout.title = " "
                    isShow = false
                }
            }
        })
    }

    override fun onResume() {
        super.onResume()
        presenter.onResume()
    }

    override fun onPause() {
        super.onPause()
        presenter.onPause()
    }

    override fun injectDependencies(applicationComponent: ApplicationComponent) {
        applicationComponent.plus(AboutActivityModule(this))
                .injectTo(this)
    }
}