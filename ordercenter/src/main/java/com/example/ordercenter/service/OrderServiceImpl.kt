package com.example.ordercenter.service

import com.example.baselibrary.data.net.RetrofitFactory
import com.example.baselibrary.ext.convert
import com.kotlin.order.data.api.OrderApi
import com.kotlin.order.data.protocol.*
import rx.Observable
import javax.inject.Inject

/**
 * Created by user on 2018/3/23.
 */
class OrderServiceImpl @Inject constructor() : OrderService {
    override fun cancelOrder(orderId: Int): Observable<String> {
        return RetrofitFactory.instance.create(OrderApi::class.java)
                .cancelOrder(CancelOrderReq(orderId))
                .convert()
    }

    override fun confirmOrder(orderId: Int): Observable<String> {
        return RetrofitFactory.instance.create(OrderApi::class.java)
                .confirmOrder(ConfirmOrderReq(orderId))
                .convert()
    }

    override fun getOrderList(orderStatus: Int): Observable<MutableList<Order>?> {
        return RetrofitFactory.instance.create(OrderApi::class.java)
                .getOrderList(GetOrderListReq(orderStatus))
                .convert()
    }

    override fun submitOrder(order: Order): Observable<String> {
        return RetrofitFactory.instance.create(OrderApi::class.java)
                .submitOrder(SubmitOrderReq(order))
                .convert()
    }

    override fun getOrderById(orderId: Int): Observable<Order> {
        return RetrofitFactory.instance.create(OrderApi::class.java)
                .getOrderById(GetOrderByIdReq(orderId))
                .convert()
    }

}