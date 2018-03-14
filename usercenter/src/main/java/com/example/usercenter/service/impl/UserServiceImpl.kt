package com.example.usercenter.service.impl

import com.example.baselibrary.data.net.RetrofitFactory
import com.example.baselibrary.data.net.UserApi
import com.example.baselibrary.data.protocol.BaseResp
import com.example.baselibrary.data.protocol.RegisterReq
import com.example.baselibrary.rx.BaseException
import com.example.usercenter.service.UserService
import rx.Observable
import rx.functions.Func1
import javax.inject.Inject

/**
 * Created by Administrator on 2018/3/7.
 */
class UserServiceImpl @Inject constructor(): UserService {
    override fun register(mobile: String, verifyCode: String, pwd: String): Observable<Boolean> {
        return RetrofitFactory.instance.create(UserApi::class.java)
                .register(RegisterReq(mobile, pwd, verifyCode))
                .flatMap(object : Func1<BaseResp<String>, Observable<Boolean>> {
                    override fun call(t: BaseResp<String>): Observable<Boolean> {
                        if (t.status != 0) {
                            return Observable.error(BaseException(t.status, t.message))
                        }
                        return Observable.just(true)
                    }

                })
    }
}