package com.example.usercenter.service

import com.example.baselibrary.data.protocol.UserInfo
import rx.Observable

/**
 * Created by Administrator on 2018/3/7.
 */
interface UserService {
    fun register(mobile: String, pwd: String, verifyCode: String): Observable<Boolean>
    fun login(mobile: String, pwd: String, pushId: String): Observable<UserInfo>
    fun editUser(userIcon: String, userName: String, userGender: String, userSign: String): Observable<UserInfo>
    fun forgetPwd(mobile: String, verifyCode: String): Observable<Boolean>
    fun resetPwd(mobile: String, pwd: String): Observable<Boolean>
}