package com.example.view

import com.example.baselibrary.presenter.view.BaseView
import com.example.data.protocol.Category
import com.example.data.protocol.Goods

/**
 * Created by Administrator on 2018/3/7.
 */
interface GoodsView : BaseView {
    fun onGetGoodsList(result: MutableList<Goods>?)
    fun onGetGoodsDetail(result: MutableList<Goods>?)
}