package com.example.ordercenter.ui.activity

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.eightbitlab.rxbus.Bus
import com.eightbitlab.rxbus.registerInBus
import com.example.baselibrary.ext.onClick
import com.example.baselibrary.ext.setVisible
import com.example.baselibrary.ui.activity.BaseMvpActivity
import com.example.baselibrary.utils.YuanFenConverter
import com.example.ordercenter.R
import com.example.ordercenter.injection.component.DaggerOrderComponent
import com.example.ordercenter.injection.module.OrderModule
import com.example.ordercenter.presenter.OrderComfirmPresenter
import com.example.ordercenter.presenter.view.OrderComfirmView
import com.example.provider.common.ProviderConstant
import com.example.provider.router.RouterPath
import com.kotlin.order.data.protocol.Order
import com.kotlin.order.event.SelectAddressEvent
import com.kotlin.order.ui.adapter.OrderGoodsAdapter
import kotlinx.android.synthetic.main.activity_order_confirm.*
import org.jetbrains.anko.startActivity

/**
 * Created by Administrator on 2018/3/28.
 */
@Route(path = RouterPath.OrderCenter.PATH_ORDER_CONFIRM)
class OrderComfirmActivity : BaseMvpActivity<OrderComfirmPresenter>(), OrderComfirmView {
    private lateinit var mAdapter: OrderGoodsAdapter
    @JvmField
    @Autowired(name = ProviderConstant.KEY_ORDER_ID)
    var mOrderId: Int = 0
    private var mCurrentOrder: Order? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_confirm)
        initView()
        initObserve()
        loadData()
    }

    private fun loadData() {
        mPresenter.getOrderById(mOrderId)
    }

    private fun initView() {
        mSelectShipTv.onClick {
            startActivity<ShipAddressActivity>()
        }
        mSubmitOrderBtn.onClick {
            mCurrentOrder?.let {  mPresenter.submitOrder(it) }

        }
        mOrderGoodsRv.layoutManager = LinearLayoutManager(this)
        mAdapter = OrderGoodsAdapter(this)
        mOrderGoodsRv.adapter = mAdapter
        mShipView.onClick {
            startActivity<ShipAddressActivity>()
        }

    }

    override fun onGetOrderByIdResult(result: Order) {
        mCurrentOrder = result
        mTotalPriceTv.text = "合计：${YuanFenConverter.changeF2YWithUnit(result.totalPrice)}"
        mAdapter.setData(result.orderGoodsList)
        updateAddressView()
    }

    private fun initObserve() {
        Bus.observe<SelectAddressEvent>()
                .subscribe {
                    t: SelectAddressEvent ->
                    run{
                        mCurrentOrder?.let {
                            it.shipAddress=t.address
                        }
                        updateAddressView()
                    }

                }
                .registerInBus(this)
    }

    private fun updateAddressView() {
        mCurrentOrder?.let {
            if (it.shipAddress == null) {
                mSelectShipTv.setVisible(true)
                mShipView.setVisible(false)
            } else {
                mSelectShipTv.setVisible(false)
                mShipView.setVisible(true)
                mShipNameTv.text = it.shipAddress!!.shipUserName + "    "
                it.shipAddress!!.shipUserMobile
                mShipAddressTv.text = it.shipAddress!!.shipAddress
            }
        }
    }
    override fun onSubmitResult(result: String) {

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