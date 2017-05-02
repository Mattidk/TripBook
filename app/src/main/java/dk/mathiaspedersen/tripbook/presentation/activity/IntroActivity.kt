package dk.mathiaspedersen.tripbook.presentation.activity

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import dk.mathiaspedersen.tripbook.App
import dk.mathiaspedersen.tripbook.R
import dk.mathiaspedersen.tripbook.presentation.custom.IntroPageTransformer
import dk.mathiaspedersen.tripbook.presentation.helper.AppSettings
import dk.mathiaspedersen.tripbook.presentation.injection.ApplicationComponent
import dk.mathiaspedersen.tripbook.presentation.injection.subcomponent.intro.IntroActivityModule
import dk.mathiaspedersen.tripbook.presentation.presenter.IntroPresenter
import dk.mathiaspedersen.tripbook.presentation.view.IntroView
import org.jetbrains.anko.clearTop
import org.jetbrains.anko.intentFor
import javax.inject.Inject


class IntroActivity : AppCompatActivity(), IntroView {

    @Inject
    lateinit var presenter: IntroPresenter

    @Inject
    lateinit var setting: AppSettings

    @BindView(R.id.view_pager)
    lateinit var viewPager: ViewPager

    @BindView(R.id.layoutDots)
    lateinit var dotsLayout: LinearLayout

    @BindView(R.id.btn_skip)
    lateinit var btnSkip: Button

    @BindView(R.id.btn_next)
    lateinit var btnNext: Button

    private val layouts: IntArray = intArrayOf(R.layout.fragment_intro_slide1, R.layout.fragment_intro_slide2, R.layout.fragment_intro_slide3, R.layout.fragment_intro_slide4)
    private var dots: Array<TextView?> = arrayOfNulls(layouts.size)
    private var myViewPagerAdapter: MyViewPagerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependencies(App.graph)
        checkIfFirstLaunch()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)
        ButterKnife.bind(this)
        MyViewPagerAdapter()


        if (Build.VERSION.SDK_INT >= 21) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_IMMERSIVE
        }

        // adding bottom dots
        addBottomDots(0)

        myViewPagerAdapter = MyViewPagerAdapter()
        viewPager.adapter = myViewPagerAdapter
        viewPager.setPageTransformer(false, IntroPageTransformer())
        viewPager.addOnPageChangeListener(viewPagerPageChangeListener)

        btnSkip.setOnClickListener { launchHomeScreen() }

        btnNext.setOnClickListener {
            // checking for last page
            // if last page home screen will be launched
            val current = getItem(+1)
            if (current < layouts.size) {
                // move to next screen
                viewPager.currentItem = current
            } else {
                launchHomeScreen()
            }
        }

    }

    private fun checkIfFirstLaunch() {
        if (!setting.isFirstTimeLaunch()) {
            launchHomeScreen()
            finish()
        }
    }

    private fun launchHomeScreen() {
        setting.setFirstTimeLaunch(false)
        startActivity(intentFor<LoginActivity>().clearTop())
        finish()
    }

    private fun addBottomDots(currentPage: Int) {
        dots = arrayOfNulls<TextView>(layouts.size)

        val colorsActive = resources.getIntArray(R.array.array_dot_active)
        val colorsInactive = resources.getIntArray(R.array.array_dot_inactive)

        dotsLayout.removeAllViews()
        for (i in 0..dots.size - 1) {
            dots[i] = TextView(this)
            dots[i]!!.text = Html.fromHtml("&#8226;")
            dots[i]!!.textSize = 35F
            dots[i]!!.setTextColor(colorsInactive[currentPage])
            dotsLayout.addView(dots[i])
        }

        if (dots.isNotEmpty())
            dots[currentPage]!!.setTextColor(colorsActive[currentPage])
    }

    private fun getItem(i: Int): Int {
        return viewPager.currentItem + i
    }

    var viewPagerPageChangeListener: ViewPager.OnPageChangeListener = object : ViewPager.OnPageChangeListener {

        override fun onPageSelected(position: Int) {
            addBottomDots(position)

            // changing the next button text 'NEXT' / 'GOT IT'
            if (position == layouts.size - 1) {
                // last page. make button text to GOT IT
                btnNext.text = getString(R.string.start)
                btnSkip.visibility = View.GONE
            } else {
                // still pages are left
                btnNext.text = getString(R.string.next)
                btnSkip.visibility = View.VISIBLE
            }
        }

        override fun onPageScrolled(arg0: Int, arg1: Float, arg2: Int) {

        }

        override fun onPageScrollStateChanged(arg0: Int) {

        }
    }

    fun injectDependencies(applicationComponent: ApplicationComponent) {
        applicationComponent.plus(IntroActivityModule(this))
                .injectTo(this)
    }

    inner class MyViewPagerAdapter : PagerAdapter() {

        private var layoutInflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            val view = layoutInflater.inflate(layouts[position], container, false)
            container.addView(view)
            return view
        }

        override fun getCount(): Int {
            return layouts.size
        }

        override fun isViewFromObject(view: View, obj: Any): Boolean {
            return view === obj
        }

        override fun destroyItem(container: ViewGroup, position: Int, obj: Any) {
            val view = obj as View
            container.removeView(view)
        }
    }
}
