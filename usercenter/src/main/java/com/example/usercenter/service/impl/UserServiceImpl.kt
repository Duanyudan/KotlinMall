package com.example.usercenter.service.impl

import com.example.baselibrary.data.net.RetrofitFactory
import com.example.baselibrary.data.net.UserApi
import com.example.baselibrary.data.protocol.*
import com.example.baselibrary.ext.convert
import com.example.baselibrary.ext.convertBoolean
import com.example.usercenter.service.UserService
import com.kotlin.user.data.protocol.EditUserReq
import rx.Observable
import javax.inject.Inject

/**
 * Created by Administrator on 2018/3/7.
 */
class UserServiceImpl @Inject constructor() : UserService {
    override fun editUser(userIcon: String, userName: String, userGender: String,
                          userSign: String): Observable<UserInfo> {
        return RetrofitFactory.instance.create(UserApi::class.java)
                .editUser(EditUserReq(userIcon, userName, userGender,userSign))
                .convert()
    }

    override fun forgetPwd(mobile: String, verifyCode: String): Observable<Boolean> {
        return RetrofitFactory.instance.create(UserApi::class.java)
                .forgetPwd(ForgetPwdReq(mobile, verifyCode))
                .convertBoolean()
    }

    override fun resetPwd(mobile: String, pwd: String): Observable<Boolean> {
        return RetrofitFactory.instance.create(UserApi::class.java)
                .resetPwd(ResetPwdReq(mobile, pwd))
                .convertBoolean()
    }


    override fun login(mobile: String, pwd: String, pushId: String): Observable<UserInfo> {
        return RetrofitFactory.instance.create(UserApi::class.java)
                .login(LoginReq(mobile, pwd, pushId))
                .convert()

    }

    override fun register(mobile: String, verifyCode: String, password: String): Observable<Boolean> {
        return RetrofitFactory.instance.create(UserApi::class.java)
                .register(RegisterReq(mobile, password, verifyCode))
                .convertBoolean()
    }

}