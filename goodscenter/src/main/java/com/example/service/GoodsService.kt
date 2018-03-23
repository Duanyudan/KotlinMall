package com.example.service

import com.example.data.protocol.Category
import com.example.data.protocol.Goods
import rx.Observable

/**
 * Created by Administrator on 2018/3/7.
 */
interface GoodsService {
   fun getGoodsList( categoryId: Int, pageNo: Int): Observable<MutableList<Goods>>
   fun getGoodsListByKeyword( keyword: String, pageNo: Int): Observable<MutableList<Goods>>
   fun getGoodsDetail( goodsId: Int): Observable<MutableList<Goods>>

}