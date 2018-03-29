package com.example.ordercenter.presenter

import com.example.baselibrary.ext.excute
import com.example.baselibrary.presenter.BasePresenter
import com.example.baselibrary.rx.BaseSubscriber
import com.example.ordercenter.presenter.view.EditShipAddressView
import com.example.ordercenter.presenter.view.OrderComfirmView
import com.example.ordercenter.service.OrderServiceImpl
import com.example.ordercenter.service.ShipAddressServiceImpl
import com.kotlin.order.data.protocol.Order
import com.kotlin.order.data.protocol.ShipAddress
import javax.inject.Inject

/**
 * Created by Administrator on 2018/3/7.
 */
class EditShipAddressPresenter @Inject constructor() : BasePresenter<EditShipAddressView>() {
    @Inject
    lateinit var shipAddressService: ShipAddressServiceImpl

    fun addShipAddress(shipUserName: String, shipUserMobile: String, shipAddress: String) {
        if (!checkNetWork()) {
            return
        }
        mView.showLoading()
        shipAddressService.addShipAddress(shipUserName, shipUserMobile, shipAddress)
                .excute(object : BaseSubscriber<String>(mView) {
                    override fun onNext(t: String) {
                        mView.onAddAddressResult(t)
                    }
                }, lifecycleProvider)
    }

    fun editShipAddress(address: ShipAddress) {
        if (!checkNetWork()) {
            return
        }
        mView.showLoading()
        shipAddressService.editShipAddress(address.id, address.shipUserName, address.shipUserMobile,
                address.shipAddress, address.shipIsDefault)
                .excute(object : BaseSubscriber<String>(mView) {
                    override fun onNext(t: String) {
                        mView.onEditAddressResult(t)
                    }
                }, lifecycleProvider)
    }
}