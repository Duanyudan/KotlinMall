package com.example.usercenter.ui.activity

import android.os.Bundle
import android.widget.Toast
import com.example.baselibrary.ui.activity.BaseMvpActivity
import com.example.usercenter.R
import com.example.usercenter.presenter.RegisterPresenter
import com.example.usercenter.view.RegisterView
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : BaseMvpActivity<RegisterPresenter>(), RegisterView {
    override fun onResult(t: Boolean) {
        Toast.makeText(this@RegisterActivity, "注册成功", Toast.LENGTH_SHORT).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        mPresenter = RegisterPresenter()
        mPresenter.mView = this
        mRegisterBtn.setOnClickListener{
            mPresenter.register("","","")
        }

    }
}
