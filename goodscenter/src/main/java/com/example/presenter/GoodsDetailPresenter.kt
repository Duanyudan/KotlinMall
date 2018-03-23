package com.example.presenter

import com.example.baselibrary.ext.excute
import com.example.baselibrary.presenter.BasePresenter
import com.example.baselibrary.rx.BaseSubscriber
import com.example.data.protocol.Goods
import com.example.service.GoodsServiceImpl
import com.example.view.GoodsDetailView
import com.example.view.GoodsView
import javax.inject.Inject

/**
 * Created by Administrator on 2018/3/7.
 */
class GoodsDetailPresenter @Inject constructor() : BasePresenter<GoodsDetailView>() {
    @Inject
    lateinit var goodsService: GoodsServiceImpl

    fun getGoodsDetail(goodsId: Int) {
        if (!checkNetWork()) {
            return
        }
        mView.showLoading()
        goodsService.getGoodsDetail(goodsId)
                .excute(object : BaseSubscriber<Goods>(mView) {
                    override fun onNext(t: Goods) {
                        mView.onGetGoodsDetail(t)
                    }
                }, lifecycleProvider)
    }
}