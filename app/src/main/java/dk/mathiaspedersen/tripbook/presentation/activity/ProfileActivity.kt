package dk.mathiaspedersen.tripbook.presentation.activity

import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.design.widget.CollapsingToolbarLayout
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.view.View
import android.view.ViewAnimationUtils
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.FrameLayout
import android.widget.ImageView
import butterknife.BindView
import butterknife.ButterKnife
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.drawable.GlideDrawable
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import dk.mathiaspedersen.tripbook.R
import dk.mathiaspedersen.tripbook.presentation.entity.UserDetail
import dk.mathiaspedersen.tripbook.presentation.injection.ApplicationComponent
import dk.mathiaspedersen.tripbook.presentation.injection.subcomponent.profile.ProfileActivityModule
import dk.mathiaspedersen.tripbook.presentation.presenter.ProfilePresenter
import dk.mathiaspedersen.tripbook.presentation.view.ProfileView
import jp.wasabeef.glide.transformations.BlurTransformation
import jp.wasabeef.glide.transformations.CropCircleTransformation
import javax.inject.Inject

class ProfileActivity : BaseActivity(), ProfileView {

    @Inject
    lateinit var presenter: ProfilePresenter

    @BindView(R.id.app_bar)
    lateinit var appBarLayout: AppBarLayout

    @BindView(R.id.toolbar_layout)
    lateinit var collapsingToolbarLayout: CollapsingToolbarLayout

    @BindView(R.id.toolbar)
    lateinit var toolbar: Toolbar

    @BindView(R.id.profile_background)
    lateinit var profileBackground: ImageView

    @BindView(R.id.profile_picture)
    lateinit var profilePicture: ImageView

    @BindView(R.id.profile_loader)
    lateinit var loader: FrameLayout

    override val layoutResource: Int = R.layout.activity_profile

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
                collapsingToolbarLayout.alpha = percentage

                if (scrollRange == -1) {
                    scrollRange = appBarLayout.totalScrollRange
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbarLayout.title = applicationContext.getString(R.string.activity_profile_title)
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
        presenter.getUserProfile()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.dispose()
    }

    override fun onGetProfileSuccess(user: UserDetail) {

        Glide.with(this).load(user.photo)
                .bitmapTransform(CropCircleTransformation(this))
                .into(profilePicture)

        Glide.with(this).load(user.photo)
                .bitmapTransform(BlurTransformation(this, 12), CenterCrop(this))
                .listener(object : RequestListener<Uri?, GlideDrawable?> {
                    override fun onResourceReady(resource: GlideDrawable?, model: Uri?, target: Target<GlideDrawable?>?, isFromMemoryCache: Boolean, isFirstResource: Boolean): Boolean {
                        loader.visibility = View.GONE

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            enterReveal()
                        }else {
                            fadeReveal()
                        }

                        return false
                    }

                    override fun onException(e: Exception?, model: Uri?, target: Target<GlideDrawable?>?, isFirstResource: Boolean): Boolean {
                        return false
                    }
                }).into(profileBackground)
    }

    fun enterReveal() {
        val cx = appBarLayout.measuredWidth / 2
        val cy = appBarLayout.measuredHeight / 2
        val finalRadius = Math.max(appBarLayout.width, appBarLayout.height) / 2
        val anim = ViewAnimationUtils.createCircularReveal(appBarLayout, cx, cy, 0.toFloat(), finalRadius.toFloat())
        appBarLayout.visibility = View.VISIBLE
        anim.start()
    }

    fun fadeReveal() {
        val mLoadAnimation = AnimationUtils.loadAnimation(this, android.R.anim.fade_out)
        mLoadAnimation.duration = 200
        mLoadAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationRepeat(animation: Animation?) {}
            override fun onAnimationStart(animation: Animation?) {}
            override fun onAnimationEnd(animation: Animation?) {
                appBarLayout.visibility = View.VISIBLE
            }
        })
        loader.startAnimation(mLoadAnimation)
    }

    override fun onGetProfileFailure(message: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
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
        applicationComponent.plus(ProfileActivityModule(this))
                .injectTo(this)
    }
}
