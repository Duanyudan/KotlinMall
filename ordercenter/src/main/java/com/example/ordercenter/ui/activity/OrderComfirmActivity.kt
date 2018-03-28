package com.example.ordercenter.ui.activity

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.example.baselibrary.ui.activity.BaseMvpActivity
import com.example.ordercenter.R
import com.example.ordercenter.injection.component.DaggerOrderComponent
import com.example.ordercenter.injection.module.OrderModule
import com.example.ordercenter.presenter.OrderComfirmPresenter
import com.example.ordercenter.presenter.view.OrderComfirmView
import com.example.provider.common.ProviderConstant
import com.example.provider.router.RouterPath
import com.kotlin.order.data.protocol.Order
import com.kotlin.order.ui.adapter.OrderGoodsAdapter
import kotlinx.android.synthetic.main.activity_order_confirm.*

/**
 * Created by Administrator on 2018/3/28.
 */
@Route(path = RouterPath.OrderCenter.PATH_ORDER_CONFIRM)
class OrderComfirmActivity : BaseMvpActivity<OrderComfirmPresenter>(), OrderComfirmView {

    private lateinit var mAdapter: OrderGoodsAdapter
    @JvmField
    @Autowired(name = ProviderConstant.KEY_ORDER_ID)
     var mOrderId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_confirm)

        initView()
        loadData()
    }

    private fun loadData() {
        mPresenter.getOrderById(mOrderId)
    }

    private fun initView() {
        mOrderGoodsRv.layoutManager = LinearLayoutManager(this)
        mAdapter = OrderGoodsAdapter(this)
        mOrderGoodsRv.adapter = mAdapter
    }

    override fun onGetOrderByIdResult(result: Order) {
        mTotalPriceTv.text= result.totalPrice.toString()
        mAdapter.setData(result.orderGoodsList)
    }

    override fun injectComponent() {
        DaggerOrderComponent.builder().activityComponent(acitivtyComponent)
                .orderModule(OrderModule())
                .build()
                .inject(this)
        mPresenter.mView = this
    }
}