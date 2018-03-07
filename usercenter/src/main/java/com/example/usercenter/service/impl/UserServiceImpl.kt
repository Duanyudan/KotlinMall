package com.example.usercenter.service.impl

import com.example.usercenter.service.UserService
import rx.Observable

/**
 * Created by Administrator on 2018/3/7.
 */
class UserServiceImpl:UserService {
    override fun register(mobile: String, verifyCode: String, pwd: String): Observable<Boolean> {
        return Observable.just(true)
    }
}