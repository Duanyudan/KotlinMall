package com.example.usercenter.presenter

import com.example.baselibrary.data.protocol.UserInfo
import com.example.baselibrary.ext.excute
import com.example.baselibrary.presenter.BasePresenter
import com.example.baselibrary.rx.BaseSubscriber
import com.example.usercenter.service.impl.UserServiceImpl
import com.example.usercenter.view.ForgetPwdView
import com.example.usercenter.view.LoginView
import javax.inject.Inject

/**
 * Created by Administrator on 2018/3/7.
 */
class ForgetPwdPresenter @Inject constructor() : BasePresenter<ForgetPwdView>() {
    @Inject
    lateinit var userService: UserServiceImpl

    fun forgetPwd(mobile: String, verifyCode: String) {
        userService.forgetPwd(mobile, verifyCode)
                .excute(object : BaseSubscriber<Boolean>(mView) {
                    override fun onNext(t: Boolean) {
                        if (t)
                            mView.onForgetPwdResult("成功")
                    }
                }, lifecycleProvider)


    }
}