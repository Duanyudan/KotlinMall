package com.example.administrator.kotlinmall.ui.activity

import android.os.Bundle
import com.example.administrator.kotlinmall.R
import com.example.baselibrary.ext.onClick
import com.example.baselibrary.ui.activity.BaseActivity
import com.example.usercenter.utils.UserPrefsUtils
import kotlinx.android.synthetic.main.activity_login.*

/**
 * Created by user on 2018/3/20.
 */
class SettingActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)
        mLoginBtn.onClick {
            UserPrefsUtils.putUserInfo(null)
            finish()
        }
    }
}