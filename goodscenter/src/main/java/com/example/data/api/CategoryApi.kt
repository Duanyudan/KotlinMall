package com.example.data.api

import com.example.baselibrary.data.protocol.BaseResp
import com.example.data.protocol.Category
import com.example.data.protocol.GetCategoryReq
import retrofit2.http.Body
import retrofit2.http.POST
import rx.Observable

/**
 * Created by user on 2018/3/20.
 */
interface CategoryApi {
    /**
     * 获取商品分类
     */
    @POST("category/getCategory")
    fun getCategory(@Body req:GetCategoryReq):
            Observable<BaseResp<MutableList<Category>>>
}