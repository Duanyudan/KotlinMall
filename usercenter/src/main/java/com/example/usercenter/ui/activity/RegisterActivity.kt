package com.example.usercenter.ui.activity

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.baselibrary.ext.onClick
import com.example.baselibrary.ui.activity.BaseMvpActivity
import com.example.usercenter.R
import com.example.usercenter.injection.component.DaggerUserComponent
import com.example.usercenter.injection.module.UserModule
import com.example.usercenter.presenter.RegisterPresenter
import com.example.usercenter.view.RegisterView
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : BaseMvpActivity<RegisterPresenter>(), RegisterView {
    override fun injectComponent() {
        DaggerUserComponent.builder()
                .activityComponent(acitivtyComponent).userModule(UserModule())
                .build().inject(this)
        mPresenter.mView = this
    }

    override fun onResult(t: String) {
        Toast.makeText(this@RegisterActivity, t, Toast.LENGTH_SHORT).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        mRegisterBtn.onClick{
            mPresenter.register(mMobileEt.text.toString(),
                    mVerifyCodeEt.text.toString(), mPwdEt.text.toString())
        }
    }

}
