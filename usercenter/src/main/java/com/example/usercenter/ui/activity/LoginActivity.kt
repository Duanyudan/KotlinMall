package com.example.usercenter.ui.activity

import android.os.Bundle
import android.view.View
import com.example.baselibrary.data.protocol.UserInfo
import com.example.baselibrary.ext.enable
import com.example.baselibrary.ext.onClick
import com.example.baselibrary.ui.activity.BaseMvpActivity
import com.example.usercenter.R
import com.example.usercenter.injection.component.DaggerUserComponent
import com.example.usercenter.injection.module.UserModule
import com.example.usercenter.presenter.LoginPresenter
import com.example.usercenter.utils.UserPrefsUtils
import com.example.usercenter.view.LoginView
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.startActivity

class LoginActivity : BaseMvpActivity<LoginPresenter>(), LoginView, View.OnClickListener {

    override fun injectComponent() {
        DaggerUserComponent.builder()
                .activityComponent(acitivtyComponent).userModule(UserModule())
                .build().inject(this)
        mPresenter.mView = this
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initView()
    }

    private fun initView() {
        mLoginBtn.enable(mMobileEt, { isBtnEnable() })
        mLoginBtn.enable(mPwdEt, { isBtnEnable() })

        mLoginBtn.onClick(this)
mForgetPwdTv.onClick(this)
        mHeaderBar.getRightView().onClick(this)

    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.mLoginBtn ->
                mPresenter.login(mMobileEt.text.toString(), mPwdEt.text.toString(), "")

            R.id.mRightTv -> {
                startActivity<RegisterActivity>()
            }

            R.id.mForgetPwdTv->{
                startActivity<ForgetPwdActivity>()
            }
        }
    }

    fun isBtnEnable(): Boolean {
        return mMobileEt.text.isNullOrEmpty().not() &&
                mPwdEt.text.isNullOrEmpty().not()
    }

    override fun onLoginResult(userInfo: UserInfo) {
        UserPrefsUtils.putUserInfo(userInfo)
        startActivity<UserInfoActivity>()
    }
}