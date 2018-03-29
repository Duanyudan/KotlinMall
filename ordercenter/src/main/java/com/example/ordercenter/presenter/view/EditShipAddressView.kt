package com.example.ordercenter.presenter.view

import com.example.baselibrary.presenter.view.BaseView
import com.kotlin.order.data.protocol.Order

/**
 * Created by Administrator on 2018/3/7.
 */
interface EditShipAddressView : BaseView {
    fun onAddAddressResult(result: String)
    fun onEditAddressResult(result: String)

}