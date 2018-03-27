package com.example.presenter

import com.example.baselibrary.ext.excute
import com.example.baselibrary.presenter.BasePresenter
import com.example.baselibrary.rx.BaseSubscriber
import com.example.baselibrary.utils.AppPrefsUtils
import com.example.common.GoodsConstant
import com.example.data.protocol.AddCartReq
import com.example.data.protocol.Goods
import com.example.service.CartServiceImpl
import com.example.service.GoodsServiceImpl
import com.example.view.GoodsDetailView
import com.example.view.GoodsView
import com.qiniu.android.common.Constants
import javax.inject.Inject

/**
 * Created by Administrator on 2018/3/7.
 */
class GoodsDetailPresenter @Inject constructor() : BasePresenter<GoodsDetailView>() {
    @Inject
    lateinit var goodsService: GoodsServiceImpl

    @Inject
    lateinit var cartService: CartServiceImpl

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

    fun addCart(req: AddCartReq) {
        if (!checkNetWork()) {
            return
        }
        mView.showLoading()
        cartService.addCart(req)
                .excute(object : BaseSubscriber<Int>(mView) {
                    override fun onNext(t: Int) {
                        AppPrefsUtils.putInt(GoodsConstant.SP_CART_SIZE,0)
                        mView.onAddCartResult(t)
                    }
                }, lifecycleProvider)
    }
}