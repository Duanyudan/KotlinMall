package com.example.ordercenter.presenter

import com.example.baselibrary.ext.excute
import com.example.baselibrary.presenter.BasePresenter
import com.example.baselibrary.rx.BaseSubscriber
import com.example.ordercenter.presenter.view.ShipAddressView
import com.example.ordercenter.service.ShipAddressServiceImpl
import com.kotlin.order.data.protocol.ShipAddress
import javax.inject.Inject

/**
 * Created by Administrator on 2018/3/7.
 */
class ShipAddressPresenter @Inject constructor() : BasePresenter<ShipAddressView>() {
    @Inject
    lateinit var shipAddressService: ShipAddressServiceImpl

    fun getShipAddressList() {
        if (!checkNetWork()) {
            return
        }
        mView.showLoading()
        shipAddressService.getShipAddressList()
                .excute(object : BaseSubscriber<MutableList<ShipAddress>?>(mView) {
                    override fun onNext(t: MutableList<ShipAddress>?) {
                        mView.onGetShipAddressList(t)
                    }
                }, lifecycleProvider)
    }

    fun setDefaultShipAddress(id: Int, shipUserName: String,
                              shipUserMobile: String, shipAddress: String,
                              shipIsDefault: Int) {
        if (!checkNetWork()) {
            return
        }
        mView.showLoading()
        shipAddressService.editShipAddress(id, shipUserName, shipUserMobile,
                shipAddress, shipIsDefault)
                .excute(object : BaseSubscriber<String>(mView) {
                    override fun onNext(t: String) {
                        mView.onSetDefultResult(t)
                    }
                }, lifecycleProvider)
    }
    fun deleteShipAddress(id: Int) {
        if (!checkNetWork()) {
            return
        }
        mView.showLoading()
        shipAddressService.deleteShipAddress(id)
                .excute(object : BaseSubscriber<String>(mView) {
                    override fun onNext(t: String) {
                        mView.onDeleteResult(t)
                    }
                }, lifecycleProvider)
    }

}