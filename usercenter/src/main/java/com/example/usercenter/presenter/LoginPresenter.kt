package com.example.usercenter.presenter

import com.example.baselibrary.data.protocol.UserInfo
import com.example.baselibrary.ext.excute
import com.example.baselibrary.presenter.BasePresenter
import com.example.baselibrary.rx.BaseSubscriber
import com.example.usercenter.service.impl.UserServiceImpl
import com.example.usercenter.view.LoginView
import com.example.usercenter.view.RegisterView
import javax.inject.Inject

/**
 * Created by Administrator on 2018/3/7.
 */
class LoginPresenter @Inject constructor() : BasePresenter<LoginView>() {
    @Inject
    lateinit var userService: UserServiceImpl

    fun login(mobile: String, pwd: String, pushId: String) {
        userService.login(mobile, pwd, pushId)
                .excute(object : BaseSubscriber<UserInfo>(mView) {
                    override fun onNext(t: UserInfo) {
                        mView.onLoginResult(t)
                    }
                }, lifecycleProvider)

    }
}