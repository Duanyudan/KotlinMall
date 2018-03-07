package com.example.usercenter.service

import rx.Observable

/**
 * Created by Administrator on 2018/3/7.
 */
interface UserService {
    fun register(mobile:String,verifyCode:String,pwd:String): Observable<Boolean>
}