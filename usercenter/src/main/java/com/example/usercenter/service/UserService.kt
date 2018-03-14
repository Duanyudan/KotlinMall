package com.example.usercenter.service

import rx.Observable

/**
 * Created by Administrator on 2018/3/7.
 */
interface UserService {
    fun register(mobile:String,pwd:String,verifyCode:String): Observable<Boolean>
}