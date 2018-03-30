package com.example.paysdk.presenter

import com.example.baselibrary.ext.excute
import com.example.baselibrary.presenter.BasePresenter
import com.example.baselibrary.rx.BaseSubscriber
import com.example.paysdk.presenter.view.PayView
import com.example.paysdk.service.PayServiceImpl
import javax.inject.Inject

/**
 * Created by user on 2018/3/30.
 */
class PayPresenter @Inject constructor():BasePresenter<PayView>() {
    @Inject
    lateinit var payService:PayServiceImpl

    fun getPaySign(orderId: Int, totalPrice: Long) {
        if (!checkNetWork()) {
            return
        }
        mView.showLoading()
        payService.getPaySign(orderId, totalPrice)
                .excute(object : BaseSubscriber<String>(mView) {
                    override fun onNext(t: String) {
                        mView.onGetPaySignResult(t)
                    }
                }, lifecycleProvider)
    }
    fun payOrder(orderId: Int) {
        if (!checkNetWork()) {
            return
        }
        mView.showLoading()
        payService.payOrder(orderId)
                .excute(object : BaseSubscriber<String>(mView) {
                    override fun onNext(t: String) {
                        mView.onPayOrderResult(t)
                    }
                }, lifecycleProvider)
    }


}