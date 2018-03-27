package com.example.service

import com.example.baselibrary.data.net.RetrofitFactory
import com.example.baselibrary.ext.convert
import com.example.data.protocol.AddCartReq
import com.example.data.protocol.CartGoods
import com.example.data.protocol.DeleteCartReq
import com.example.data.protocol.SubmitCartReq
import com.kotlin.goods.data.api.CartApi
import rx.Observable
import javax.inject.Inject

/**
 * Created by user on 2018/3/23.
 */
class CartServiceImpl @Inject constructor() : CartService {
    override fun getCartList(): Observable<MutableList<CartGoods>?> {
        return RetrofitFactory.instance.create(CartApi::class.java)
                .getCartList()
                .convert()

    }

    override fun addCart(req: AddCartReq): Observable<Int> {
        return RetrofitFactory.instance.create(CartApi::class.java)
                .addCart(req)
                .convert()
    }

    override fun deleteCartList(req: DeleteCartReq): Observable<String> {
        return RetrofitFactory.instance.create(CartApi::class.java)
                .deleteCartList(req)
                .convert()
    }

    override fun submitCart(req: SubmitCartReq): Observable<Int> {
        return RetrofitFactory.instance.create(CartApi::class.java)
                .submitCart(req)
                .convert()
    }
}