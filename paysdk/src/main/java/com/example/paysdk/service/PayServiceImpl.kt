package com.example.paysdk.service

import com.example.baselibrary.data.net.RetrofitFactory
import com.example.baselibrary.ext.convert
import com.kotlin.pay.data.api.PayApi
import com.kotlin.pay.data.protocol.GetPaySignReq
import com.kotlin.pay.data.protocol.PayOrderReq
import rx.Observable
import javax.inject.Inject

/**
 * Created by user on 2018/3/30.
 */
class PayServiceImpl @Inject constructor() : PayService {
    override fun getPaySign(orderId: Int, totalPrice: Long): Observable<String> {
        return RetrofitFactory.instance.create(PayApi::class.java)
                .getPaySign(GetPaySignReq(orderId, totalPrice))
                .convert()
    }

    override fun payOrder(orderId: Int): Observable<String> {
        return RetrofitFactory.instance.create(PayApi::class.java)
                .payOrder(PayOrderReq(orderId))
                .convert()
    }
}