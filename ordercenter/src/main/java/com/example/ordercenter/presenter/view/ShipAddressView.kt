package com.example.ordercenter.presenter.view

import com.example.baselibrary.presenter.view.BaseView
import com.kotlin.order.data.protocol.ShipAddress

/**
 * Created by Administrator on 2018/3/7.
 */
interface ShipAddressView : BaseView {
    fun onGetShipAddressList(result: MutableList<ShipAddress>?)
    fun onSetDefultResult(result: String)
    fun onDeleteResult(result: String)
}