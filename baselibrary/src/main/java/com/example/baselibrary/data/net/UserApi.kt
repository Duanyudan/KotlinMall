package com.example.baselibrary.data.net

import com.example.baselibrary.data.protocol.BaseResp
import com.example.baselibrary.data.protocol.RegisterReq
import retrofit2.http.Body
import retrofit2.http.POST
import rx.Observable

/**
 * Created by user on 2018/3/14.
 */
interface UserApi {
    @POST("userCenter/register")
    fun register(@Body req: RegisterReq):Observable<BaseResp<String>>
}