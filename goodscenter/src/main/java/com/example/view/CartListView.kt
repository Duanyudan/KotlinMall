package com.example.view

import com.example.baselibrary.presenter.view.BaseView
import com.example.data.protocol.CartGoods
import com.example.data.protocol.Goods

/**
 * Created by Administrator on 2018/3/7.
 */
interface CartListView : BaseView {
    fun onGetCartList(result: MutableList<CartGoods>?)

    fun onDelete(result: String)

    fun onSubmint(result: Int)
}