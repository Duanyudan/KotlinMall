package com.example.service

import com.example.baselibrary.data.net.RetrofitFactory
import com.example.baselibrary.ext.convert
import com.example.data.api.GoodsApi
import com.example.data.protocol.GetGoodsDetailReq
import com.example.data.protocol.GetGoodsListByKeywordReq
import com.example.data.protocol.GetGoodsListReq
import com.example.data.protocol.Goods
import rx.Observable
import javax.inject.Inject

/**
 * Created by user on 2018/3/23.
 */
class GoodsServiceImpl @Inject constructor() : GoodsService {
    override fun getGoodsList(categoryId: Int, pageNo: Int): Observable<MutableList<Goods>> {
        return RetrofitFactory.instance.create(GoodsApi::class.java)
                .getGoodsList(GetGoodsListReq(categoryId, pageNo))
                .convert()
    }

    override fun getGoodsListByKeyword(keyword: String, pageNo: Int): Observable<MutableList<Goods>> {
        return RetrofitFactory.instance.create(GoodsApi::class.java)
                .getGoodsListByKeyword(GetGoodsListByKeywordReq(keyword, pageNo))
                .convert()
    }

    override fun getGoodsDetail(goodsId: Int): Observable<MutableList<Goods>> {
        return RetrofitFactory.instance.create(GoodsApi::class.java)
                .getGoodsDetail(GetGoodsDetailReq(goodsId))
                .convert()
    }

}