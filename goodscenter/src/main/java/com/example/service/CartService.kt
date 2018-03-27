package com.example.service

import com.example.data.protocol.AddCartReq
import com.example.data.protocol.CartGoods
import com.example.data.protocol.DeleteCartReq
import com.example.data.protocol.SubmitCartReq
import retrofit2.http.Body
import rx.Observable

/**
 * Created by Administrator on 2018/3/7.
 */
interface CartService {
    fun getCartList(): Observable<MutableList<CartGoods>?>

    fun addCart(@Body req: AddCartReq): Observable<Int>

    fun deleteCartList(@Body req: DeleteCartReq): Observable<String>

    fun submitCart(@Body req: SubmitCartReq): Observable<Int>

}