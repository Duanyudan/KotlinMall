package com.example.usercenter.presenter

import com.example.baselibrary.ext.excute
import com.example.baselibrary.presenter.BasePresenter
import com.example.baselibrary.rx.BaseSubscriber
import com.example.usercenter.service.impl.UserServiceImpl
import com.example.usercenter.view.ForgetPwdView
import com.example.usercenter.view.ResetPwdView
import javax.inject.Inject

/**
 * Created by Administrator on 2018/3/7.
 */
class ResetPwdPresenter @Inject constructor() : BasePresenter<ResetPwdView>() {
    @Inject
    lateinit var userService: UserServiceImpl

    fun resetPwd(mobile: String, verifyCode: String) {
        userService.resetPwd(mobile, verifyCode)
                .excute(object : BaseSubscriber<Boolean>(mView) {
                    override fun onNext(t: Boolean) {
                        if (t)
                            mView.onResetPwdResult("重置密码成功")
                    }
                }, lifecycleProvider)


    }
}