package com.example.view

import com.example.baselibrary.presenter.view.BaseView
import com.example.data.protocol.Goods

/**
 * Created by Administrator on 2018/3/7.
 */
interface GoodsDetailView : BaseView {
    fun onGetGoodsDetail(result: Goods)

    fun onAddCartResult(result:Int)
}