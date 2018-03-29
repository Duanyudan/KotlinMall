package com.example.ordercenter.presenter

import com.example.baselibrary.ext.excute
import com.example.baselibrary.presenter.BasePresenter
import com.example.baselibrary.rx.BaseSubscriber
import com.example.ordercenter.presenter.view.OrderListView
import com.example.ordercenter.service.OrderServiceImpl
import com.kotlin.order.data.protocol.Order
import javax.inject.Inject

/**
 * Created by Administrator on 2018/3/7.
 */
class OrderListPresenter @Inject constructor() : BasePresenter<OrderListView>() {
    @Inject
    lateinit var orderService: OrderServiceImpl

    fun getOrderList(orderStatus: Int) {
        if (!checkNetWork()) {
            return
        }
        mView.showLoading()
        orderService.getOrderList(orderStatus)
                .excute(object : BaseSubscriber<MutableList<Order>?>(mView) {
                    override fun onNext(t: MutableList<Order>?) {
                        mView.onGetOrderListResult(t)
                    }
                }, lifecycleProvider)
    }

    fun cancelOrder(orderId: Int) {
        if (!checkNetWork()) {
            return
        }
        mView.showLoading()
        orderService.cancelOrder(orderId)
                .excute(object : BaseSubscriber<String>(mView) {
                    override fun onNext(t: String) {
                        mView.onCancleResult(t)
                    }
                }, lifecycleProvider)
    }

    fun confirmOrder(orderId: Int) {
        if (!checkNetWork()) {
            return
        }
        mView.showLoading()
        orderService.confirmOrder(orderId)
                .excute(object : BaseSubscriber<String>(mView) {
                    override fun onNext(t: String) {
                        mView.onComfirmReslut(t)
                    }
                }, lifecycleProvider)
    }

}