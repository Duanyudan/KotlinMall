package com.example.presenter

import com.example.baselibrary.ext.excute
import com.example.baselibrary.presenter.BasePresenter
import com.example.baselibrary.rx.BaseSubscriber
import com.example.data.protocol.CartGoods
import com.example.data.protocol.DeleteCartReq
import com.example.data.protocol.SubmitCartReq
import com.example.service.CartServiceImpl
import com.example.view.CartListView
import retrofit2.http.Body
import javax.inject.Inject

/**
 * Created by Administrator on 2018/3/7.
 */
class CartListPresenter @Inject constructor() : BasePresenter<CartListView>() {

    @Inject
    lateinit var cartService: CartServiceImpl

    fun getCartList() {
        if (!checkNetWork()) {
            return
        }
        mView.showLoading()
        cartService.getCartList()
                .excute(object : BaseSubscriber<MutableList<CartGoods>?>(mView) {
                    override fun onNext(t: MutableList<CartGoods>?) {
                        mView.onGetCartList(t)
                    }
                }, lifecycleProvider)
    }

    fun deleteCartList(req: DeleteCartReq) {
        if (!checkNetWork()) {
            return
        }
        mView.showLoading()
        cartService.deleteCartList(req)
                .excute(object : BaseSubscriber<String>(mView) {
                    override fun onNext(t: String) {
                        mView.onDelete(t)
                    }
                }, lifecycleProvider)
    }

    fun submitCart( req: SubmitCartReq) {
        if (!checkNetWork()) {
            return
        }
        mView.showLoading()
        cartService.submitCart(req)
                .excute(object : BaseSubscriber<Int>(mView) {
                    override fun onNext(t: Int) {
                        mView.onSubmint(t)
                    }
                }, lifecycleProvider)
    }
}