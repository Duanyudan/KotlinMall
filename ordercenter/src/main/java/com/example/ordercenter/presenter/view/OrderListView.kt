package com.example.ordercenter.presenter.view

import com.example.baselibrary.presenter.view.BaseView
import com.kotlin.order.data.protocol.Order

/**
 * Created by Administrator on 2018/3/7.
 */
interface OrderListView : BaseView {
    fun onGetOrderListResult(result: MutableList<Order>?)
    fun onCancleResult(result:String)
    fun onComfirmReslut(result: String)

}