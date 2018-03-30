package com.example.paysdk.presenter.view

import com.example.baselibrary.presenter.view.BaseView

/**
 * Created by user on 2018/3/30.
 */
interface PayView : BaseView {
    fun onGetPaySignResult(result: String)
    fun onPayOrderResult(result: String)

}