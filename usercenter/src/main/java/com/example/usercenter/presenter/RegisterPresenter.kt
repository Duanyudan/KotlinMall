package com.example.usercenter.presenter

import com.example.baselibrary.ext.excute
import com.example.baselibrary.presenter.BasePresenter
import com.example.baselibrary.rx.BaseSubscriber
import com.example.usercenter.service.impl.UserServiceImpl
import com.example.usercenter.view.RegisterView
import javax.inject.Inject

/**
 * Created by Administrator on 2018/3/7.
 */
class RegisterPresenter @Inject constructor(): BasePresenter<RegisterView>() {
    @Inject
    lateinit var userService:UserServiceImpl
    fun register(mobile: String, verifyCode: String,pwd: String) {
        userService.register(mobile,verifyCode,pwd)
                .excute(object :BaseSubscriber<Boolean>(){
                    override fun onNext(t: Boolean) {
                        if (t)
                        mView.onResult("注册成功")
                    }
                },lifecycleProvider)

    }
}