package com.example.data.api

import com.example.baselibrary.data.protocol.BaseResp
import com.example.data.protocol.*
import retrofit2.http.Body
import retrofit2.http.POST
import rx.Observable

/**
 * Created by user on 2018/3/20.
 */
interface GoodsApi {
    /**
     * 获取商品列表
     */
    @POST("goods/getGoodsList")
    fun getGoodsList(@Body req: GetGoodsListReq):
            Observable<BaseResp<MutableList<Goods>>>
    /**
     * 关键字获取商品列表
     */
    @POST("goods/getGoodsListByKeyword")
    fun getGoodsListByKeyword(@Body req: GetGoodsListByKeywordReq):
            Observable<BaseResp<MutableList<Goods>>>
    /**
     * 获取商品详情
     */
    @POST("goods/getGoodsDetail")
    fun getGoodsDetail(@Body req: GetGoodsDetailReq):
            Observable<BaseResp<MutableList<Goods>>>
}