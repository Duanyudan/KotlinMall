package com.example.ordercenter.ui.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bigkoo.alertview.AlertView
import com.bigkoo.alertview.OnItemClickListener
import com.example.baselibrary.ext.startLoading
import com.example.baselibrary.ui.fragment.BaseMvpFragment
import com.example.ordercenter.R
import com.example.ordercenter.injection.component.DaggerOrderComponent
import com.example.ordercenter.injection.module.OrderModule
import com.example.ordercenter.presenter.OrderListPresenter
import com.example.ordercenter.presenter.view.OrderListView
import com.example.ordercenter.ui.activity.OrderDetailActivity
import com.example.provider.common.ProviderConstant
import com.kennyc.view.MultiStateView
import com.kotlin.base.ui.adapter.BaseRecyclerViewAdapter
import com.kotlin.order.common.OrderConstant
import com.kotlin.order.data.protocol.Order
import com.kotlin.order.data.protocol.ShipAddress
import com.kotlin.order.ui.adapter.OrderAdapter
import kotlinx.android.synthetic.main.fragment_order.*
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.startService

/**
 * Created by user on 2018/3/29.
 */
class OrderFragment : BaseMvpFragment<OrderListPresenter>(), OrderListView {
    private lateinit var mAdapter: OrderAdapter
    override fun injectComponent() {
        DaggerOrderComponent.builder().activityComponent(acitivtyComponent)
                .orderModule(OrderModule())
                .build()
                .inject(this)
        mPresenter.mView = this
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater?.inflate(R.layout.fragment_order, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        loadData()
    }

    private fun initView() {
        mOrderRv.layoutManager = LinearLayoutManager(activity)
        mAdapter = OrderAdapter(activity)
        mOrderRv.adapter = mAdapter
        mAdapter.listener = object : OrderAdapter.OnOptClickListener {
            override fun onOptClick(optType: Int, order: Order) {
                when (optType) {
                    OrderConstant.OPT_ORDER_PAY -> {

                    }
                    OrderConstant.OPT_ORDER_CANCEL -> {
                 showDeleteAlert(order)
                    }
                    OrderConstant.OPT_ORDER_CONFIRM -> {
                        mPresenter.confirmOrder(order.id)
                    }
                }
            }
        }
        mAdapter.setOnItemClickListener(object :BaseRecyclerViewAdapter.OnItemClickListener<Order>{
            override fun onItemClick(item: Order, position: Int) {
               startActivity<OrderDetailActivity>(ProviderConstant.KEY_ORDER_ID to item.id)
            }

        })
    }

    private fun loadData() {
        mMultiStateView.startLoading()
        mPresenter.getOrderList(arguments.getInt(OrderConstant.KEY_ORDER_STATUS, -1))

    }
    fun showDeleteAlert(order:Order) {
        AlertView("取消订单", "确定取消？", "取消", null, arrayOf("确定"),
                activity, AlertView.Style.Alert,
                object : OnItemClickListener {
                    override fun onItemClick(o: Any?, position: Int) {
                        if (position==0){
                            mPresenter.cancelOrder(order.id)
                        }
                    }
                }).show()
    }
    override fun onGetOrderListResult(result: MutableList<Order>?) {
        if (result != null && result.size > 0) {
            mAdapter.setData(result)
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_CONTENT
        } else {
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_EMPTY
        }
    }

    override fun onCancleResult(result: String) {
        loadData()
    }

    override fun onComfirmReslut(result: String) {
        loadData()
    }
}