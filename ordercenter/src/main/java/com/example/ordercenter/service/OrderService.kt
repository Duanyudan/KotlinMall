package com.example.ordercenter.service

import com.example.baselibrary.data.protocol.BaseResp
import com.kotlin.order.data.protocol.GetOrderByIdReq
import com.kotlin.order.data.protocol.Order
import rx.Observable

/**
 * Created by Administrator on 2018/3/7.
 */
interface OrderService {
   /*
        根据ID获取订单
     */
   fun getOrderById( orderId: Int): Observable<Order>
}