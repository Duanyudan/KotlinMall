package com.example.ordercenter.service

import com.example.baselibrary.data.net.RetrofitFactory
import com.example.baselibrary.ext.convert
import com.kotlin.order.data.api.OrderApi
import com.kotlin.order.data.protocol.GetOrderByIdReq
import com.kotlin.order.data.protocol.Order
import rx.Observable
import javax.inject.Inject

/**
 * Created by user on 2018/3/23.
 */
class OrderServiceImpl @Inject constructor(): OrderService{
    override fun getOrderById(orderId: Int): Observable<Order> {
        return RetrofitFactory.instance.create(OrderApi::class.java)
                .getOrderById(GetOrderByIdReq(orderId))
                .convert()
    }

}