package com.example.usercenter.service.impl

import com.example.baselibrary.data.net.RetrofitFactory
import com.example.baselibrary.data.net.UploadApi
import com.example.baselibrary.data.net.UserApi
import com.example.baselibrary.data.protocol.*
import com.example.baselibrary.ext.convert
import com.example.usercenter.service.UploadService
import com.example.usercenter.service.UserService
import rx.Observable
import javax.inject.Inject

/**
 * Created by Administrator on 2018/3/7.
 */
class UploadServiceImpl @Inject constructor() : UploadService {
    override fun getUploadToken(): Observable<String> {
        return RetrofitFactory.instance.create(UploadApi::class.java)
                .getUploadToken()
                .convert()
    }

}