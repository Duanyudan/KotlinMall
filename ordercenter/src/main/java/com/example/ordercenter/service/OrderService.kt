package com.example.ordercenter.service

import com.kotlin.order.data.protocol.Order
import rx.Observable

/**
 * Created by Administrator on 2018/3/7.
 */
interface OrderService {
    /*
         根据ID获取订单
      */
    fun getOrderById(orderId: Int): Observable<Order>

    /*
        提交订单
     */
    fun submitOrder(order: Order): Observable<String>

    /*
       取消订单
    */
    fun cancelOrder(orderId: Int): Observable<String>

    /*
        确认订单
     */
    fun confirmOrder(orderId: Int): Observable<String>

    /*
        根据订单状态查询查询订单列表
     */
    fun getOrderList(orderStatus: Int): Observable<MutableList<Order>?>

}