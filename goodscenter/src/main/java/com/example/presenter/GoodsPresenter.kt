package com.example.presenter

import com.example.baselibrary.ext.excute
import com.example.baselibrary.presenter.BasePresenter
import com.example.baselibrary.rx.BaseSubscriber
import com.example.data.protocol.Category
import com.example.data.protocol.Goods
import com.example.service.CategoryServiceImpl
import com.example.service.GoodsServiceImpl
import com.example.view.CategoryView
import com.example.view.GoodsView
import javax.inject.Inject

/**
 * Created by Administrator on 2018/3/7.
 */
class GoodsPresenter @Inject constructor() : BasePresenter<GoodsView>() {
    @Inject
    lateinit var goodsService: GoodsServiceImpl

    fun getGoodsList(categoryId: Int, pageNo: Int) {
        if (!checkNetWork()) {
            return
        }
        mView.showLoading()
        goodsService.getGoodsList(categoryId, pageNo)
                .excute(object : BaseSubscriber<MutableList<Goods>>(mView) {
                    override fun onNext(t: MutableList<Goods>) {
                        mView.onGetGoodsList(t)
                    }
                }, lifecycleProvider)
    }

    fun getGoodsListByKeyword(keyword: String, pageNo: Int) {
        if (!checkNetWork()) {
            return
        }
        mView.showLoading()
        goodsService.getGoodsListByKeyword(keyword, pageNo)
                .excute(object : BaseSubscriber<MutableList<Goods>>(mView) {
                    override fun onNext(t: MutableList<Goods>) {
                        mView.onGetGoodsList(t)
                    }
                }, lifecycleProvider)
    }

    fun getGoodsDetail(goodsId: Int) {
        if (!checkNetWork()) {
            return
        }
        mView.showLoading()
        goodsService.getGoodsDetail(goodsId)
                .excute(object : BaseSubscriber<MutableList<Goods>>(mView) {
                    override fun onNext(t: MutableList<Goods>) {
                        mView.onGetGoodsDetail(t)
                    }
                }, lifecycleProvider)
    }
}