package com.example.ordercenter.ui.activity

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.eightbitlab.rxbus.Bus
import com.example.baselibrary.ui.activity.BaseMvpActivity
import com.example.baselibrary.utils.YuanFenConverter
import com.example.ordercenter.R
import com.example.ordercenter.injection.component.DaggerOrderComponent
import com.example.ordercenter.injection.module.OrderModule
import com.example.ordercenter.presenter.OrderDetailPresenter
import com.example.ordercenter.presenter.view.OrderDetailView
import com.example.provider.common.ProviderConstant
import com.kotlin.order.data.protocol.Order
import com.kotlin.order.ui.adapter.OrderGoodsAdapter

import kotlinx.android.synthetic.main.activity_order_detail.*

/**
 * Created by user on 2018/3/29.
 */
class OrderDetailActivity : BaseMvpActivity<OrderDetailPresenter>(), OrderDetailView {
    private lateinit var mAdapter: OrderGoodsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_detail)
        initView()
        loadData()
    }

    private fun loadData() {
        mPresenter.getOrderDetail(intent.getIntExtra(ProviderConstant.KEY_ORDER_ID,-1))
    }

    private fun initView() {
        mOrderGoodsRv.layoutManager = LinearLayoutManager(this)
        mAdapter = OrderGoodsAdapter(this)
        mOrderGoodsRv.adapter = mAdapter
    }

    override fun onGetOrderDetailResult(result: Order) {
        mShipNameTv.setContentText(result.shipAddress!!.shipUserName)
        mShipMobileTv.setContentText(result.shipAddress!!.shipUserMobile)
        mShipAddressTv.setContentText(result.shipAddress!!.shipAddress)
        mTotalPriceTv.setContentText(YuanFenConverter.changeF2YWithUnit(result.totalPrice))
        mAdapter.setData(result.orderGoodsList)
    }

    override fun onDestroy() {
        super.onDestroy()
        Bus.unregister(this)
    }

    override fun injectComponent() {
        DaggerOrderComponent.builder().activityComponent(acitivtyComponent)
                .orderModule(OrderModule())
                .build()
                .inject(this)
        mPresenter.mView = this
    }
}