package com.example.usercenter.presenter

import com.example.baselibrary.ext.excute
import com.example.baselibrary.presenter.BasePresenter
import com.example.baselibrary.rx.BaseSubscriber
import com.example.usercenter.service.impl.UserServiceImpl
import com.example.usercenter.view.RegisterView
import rx.Observable
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by Administrator on 2018/3/7.
 */
class RegisterPresenter : BasePresenter<RegisterView>() {
    fun register(mobile: String, verifyCode: String,pwd: String) {
        val userService=UserServiceImpl()
        userService.register(mobile,verifyCode,pwd)
                .excute(object :BaseSubscriber<Boolean>(){
                    override fun onNext(t: Boolean) {
                        mView.onResult(t)
                    }
                })

    }
}