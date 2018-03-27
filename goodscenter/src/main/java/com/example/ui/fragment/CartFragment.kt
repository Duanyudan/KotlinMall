package com.example.ui.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.eightbitlab.rxbus.Bus
import com.eightbitlab.rxbus.registerInBus
import com.example.baselibrary.ext.onClick
import com.example.baselibrary.ext.setVisible
import com.example.baselibrary.ext.startLoading
import com.example.baselibrary.ui.fragment.BaseMvpFragment
import com.example.baselibrary.utils.AppPrefsUtils
import com.example.baselibrary.utils.YuanFenConverter
import com.example.common.GoodsConstant
import com.example.data.protocol.CartGoods
import com.example.data.protocol.DeleteCartReq
import com.example.data.protocol.SubmitCartReq
import com.example.goodscenter.R
import com.example.injection.component.DaggerCartComponent
import com.example.injection.module.CartModule
import com.example.presenter.CartListPresenter
import com.example.view.CartListView
import com.kennyc.view.MultiStateView
import com.kotlin.goods.event.CartAllCheckedEvent
import com.kotlin.goods.event.UpdateCartSizeEvent
import com.kotlin.goods.event.UpdateTotalPriceEvent
import com.kotlin.goods.ui.adapter.CartGoodsAdapter
import kotlinx.android.synthetic.main.fragment_cart.*
import org.jetbrains.anko.support.v4.toast

/**
 * Created by Administrator on 2018/3/20.
 */
class CartFragment : BaseMvpFragment<CartListPresenter>(), CartListView {


    private lateinit var mAdapter: CartGoodsAdapter
    private var mTotalPrice: Long = 0
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater?.inflate(R.layout.fragment_cart, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()

        initObserve()
    }

    override fun onStart() {
        super.onStart()
        loadData()
    }

    private fun initObserve() {
        Bus.observe<CartAllCheckedEvent>()
                .subscribe { t: CartAllCheckedEvent ->
                    run {
                        mAllCheckedCb.isChecked = t.isAllChecked
                        updateTotalPrice()
                    }
                }
                .registerInBus(this)
        Bus.observe<UpdateTotalPriceEvent>()
                .subscribe {
                    updateTotalPrice()
                }
                .registerInBus(this)
    }

    private fun initView() {
        mCartGoodsRv.layoutManager = LinearLayoutManager(context)
        mAdapter = CartGoodsAdapter(context)
        mCartGoodsRv.adapter = mAdapter
        mAllCheckedCb.onClick {
            for (item in mAdapter.dataList) {
                item.isSelected = mAllCheckedCb.isChecked
            }
            mAdapter.notifyDataSetChanged()
            updateTotalPrice()
        }

        mHeaderBar.getRightView().onClick {
            refreshEditStatus()
        }

        mDeleteBtn.onClick {
            val cartIdList: MutableList<Int> = arrayListOf()
            mAdapter.dataList.filter { it.isSelected }
                    .mapTo(cartIdList) {
                        it.id
                    }
            if (cartIdList.size == 0) {
                toast("请选择要删除的数据")
            } else {
                mPresenter.deleteCartList(DeleteCartReq(cartIdList))
            }
        }

        mSettleAccountsBtn.onClick {
            val cartGoodsList: MutableList<CartGoods> = arrayListOf()
            mAdapter.dataList.filter { it.isSelected }
                    .mapTo(cartGoodsList) {
                        it
                    }
            if (cartGoodsList.size == 0) {
                toast("请选择要结算的数据")
            } else {
                mPresenter.submitCart(SubmitCartReq(cartGoodsList,mTotalPrice))
            }
        }
    }

    private fun refreshEditStatus() {
        val isEditStatus = getString(R.string.common_edit) == mHeaderBar.getRightText()
        mTotalPriceTv.setVisible(isEditStatus.not())
        mSettleAccountsBtn.setVisible(isEditStatus.not())
        mDeleteBtn.setVisible(isEditStatus)
        mHeaderBar.getRightView().text = if (isEditStatus) getString(R.string.common_complete)
        else getString(R.string.common_edit)
    }

    private fun loadData() {
        mMultiStateView.startLoading()
        mPresenter.getCartList()
    }

    fun updateTotalPrice() {
        mTotalPrice = mAdapter.dataList.filter { it.isSelected }
                .map { it.goodsCount * it.goodsPrice }
                .sum()
        mTotalPriceTv.text = "合计${YuanFenConverter.changeF2YWithUnit(mTotalPrice)}"
    }

    override fun onGetCartList(result: MutableList<CartGoods>?) {
        if (result != null && result.size > 0) {
            mAdapter.setData(result)
            mHeaderBar.getRightView().setVisible(true)
            mAllCheckedCb.isChecked=false
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_CONTENT
        } else {
            mHeaderBar.getRightView().setVisible(false)
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_EMPTY
        }
        AppPrefsUtils.putInt(GoodsConstant.SP_CART_SIZE, result?.size ?: 0)
        Bus.send(UpdateCartSizeEvent())
        updateTotalPrice()
    }

    override fun onDelete(result: String) {
        loadData()
        refreshEditStatus()
    }
    override fun onSubmint(result: Int) {

    }
    override fun injectComponent() {
        DaggerCartComponent.builder()
                .activityComponent(acitivtyComponent).cartModule(CartModule())
                .build().inject(this)
        mPresenter.mView = this
    }

    override fun onDestroy() {
        super.onDestroy()
        Bus.unregister(this)
    }

    fun setBackVisible(isVisible: Boolean){
        mHeaderBar.getLeftView().setVisible(isVisible)
    }
}