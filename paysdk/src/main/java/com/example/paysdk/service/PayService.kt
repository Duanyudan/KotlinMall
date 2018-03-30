package com.example.paysdk.service

import rx.Observable

/**
 * Created by user on 2018/3/30.
 */
interface PayService {
    /*
    获取支付宝支付签名
 */
    fun getPaySign(orderId: Int,  totalPrice: Long): Observable<String>

    /*
        刷新订单状态，已支付
     */
    fun payOrder(orderId:Int): Observable<String>
}