package dk.mathiaspedersen.tripbook.presentation.activity

import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.RelativeLayout
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.common.api.GoogleApiClient
import com.google.firebase.auth.FirebaseAuth
import dk.mathiaspedersen.tripbook.R
import dk.mathiaspedersen.tripbook.presentation.helper.ViewHelper
import dk.mathiaspedersen.tripbook.presentation.injection.ApplicationComponent
import dk.mathiaspedersen.tripbook.presentation.injection.subcomponent.login.LoginActivityModule
import dk.mathiaspedersen.tripbook.presentation.presenter.LoginPresenter
import dk.mathiaspedersen.tripbook.presentation.view.LoginView
import org.jetbrains.anko.clearTask
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.newTask
import javax.inject.Inject

class LoginActivity : BaseActivity(), LoginView {

    override val layoutResource: Int = R.layout.activity_login

    @BindView(R.id.activity_login)
    lateinit var rootview: RelativeLayout

    @Inject
    lateinit var presenter: LoginPresenter

    @Inject
    lateinit var client: GoogleApiClient

    @Inject
    lateinit var auth: FirebaseAuth

    @Inject
    lateinit var viewHelper: ViewHelper

    var anim: AnimationDrawable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ButterKnife.bind(this)
        makeActivityFullscreenLollipop()
        setBackground()
        checkUserStatus()
    }

    private fun checkUserStatus() {
        if (auth.currentUser != null){
            startActivity(intentFor<HostActivity>().clearTask().newTask())
            finish()
        }
    }

    override fun onResume() {
        super.onResume()
        if (anim != null && !anim!!.isRunning)
            anim!!.start()
    }

    override fun onPause() {
        super.onPause()
        if (anim != null && anim!!.isRunning())
            anim!!.stop()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.dispose()
    }

    private fun setBackground() {
        anim = rootview.background as AnimationDrawable
        anim?.setEnterFadeDuration(12000)
        anim?.setExitFadeDuration(6000)
    }

    fun makeActivityFullscreenLollipop() {
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
    }

    override fun successfulLogin() {
        viewHelper.hideProgress()
        startActivity(intentFor<HostActivity>().clearTask().newTask())
        finish()
    }

    override fun unsuccessfullogin(message: String) {
        Log.d("TESTER", message)
    }

    @OnClick(R.id.google_provider)
    fun signIn(view: View) {
        viewHelper.showProgress(R.string.activity_login_dialog_sign_up_loading)
        val signInIntent = Auth.GoogleSignInApi.getSignInIntent(client)
        startActivityForResult(signInIntent, 123)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 123) {
            val result = Auth.GoogleSignInApi.getSignInResultFromIntent(data)
            if (result.isSuccess) {
                val account = result.signInAccount
                if (account != null) {
                    presenter.signInWithGoogle(account)
                }
            }else {
                viewHelper.hideProgress()
            }
        }
    }

    override fun injectDependencies(applicationComponent: ApplicationComponent) {
        applicationComponent.plus(LoginActivityModule(this))
                .injectTo(this)
    }
}