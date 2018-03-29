package com.example.ordercenter.presenter

import com.example.baselibrary.ext.excute
import com.example.baselibrary.presenter.BasePresenter
import com.example.baselibrary.rx.BaseSubscriber
import com.example.ordercenter.presenter.view.OrderDetailView
import com.example.ordercenter.presenter.view.OrderListView
import com.example.ordercenter.service.OrderServiceImpl
import com.kotlin.order.data.protocol.Order
import javax.inject.Inject

/**
 * Created by Administrator on 2018/3/7.
 */
class OrderDetailPresenter @Inject constructor() : BasePresenter<OrderDetailView>() {
    @Inject
    lateinit var orderService: OrderServiceImpl

    fun getOrderDetail(orderId: Int) {
        if (!checkNetWork()) {
            return
        }
        mView.showLoading()
        orderService.getOrderById(orderId)
                .excute(object : BaseSubscriber<Order>(mView) {
                    override fun onNext(t: Order) {
                        mView.onGetOrderDetailResult(t)
                    }
                }, lifecycleProvider)
    }

}