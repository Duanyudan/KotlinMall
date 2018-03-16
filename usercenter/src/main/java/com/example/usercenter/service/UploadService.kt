package com.example.usercenter.service

import com.example.baselibrary.data.protocol.BaseResp
import rx.Observable

/**
 * Created by Administrator on 2018/3/7.
 */
interface UploadService {
 fun getUploadToken():Observable<String>
}