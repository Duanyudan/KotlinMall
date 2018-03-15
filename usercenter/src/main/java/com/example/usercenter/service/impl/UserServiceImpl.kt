package com.example.usercenter.service.impl

import com.example.baselibrary.data.net.RetrofitFactory
import com.example.baselibrary.data.net.UserApi
import com.example.baselibrary.data.protocol.BaseResp
import com.example.baselibrary.data.protocol.RegisterReq
import com.example.baselibrary.ext.convertBoolean
import com.example.baselibrary.rx.BaseException
import com.example.baselibrary.rx.BaseFuncBoolean
import com.example.usercenter.service.UserService
import rx.Observable
import rx.functions.Func1
import javax.inject.Inject

/**
 * Created by Administrator on 2018/3/7.
 */
class UserServiceImpl @Inject constructor(): UserService {
    override fun register(mobile: String, verify: String, password: String): Observable<Boolean> {
        return RetrofitFactory.instance.create(UserApi::class.java)
                .register(RegisterReq(mobile, password, verify))
                .convertBoolean()
    }
}