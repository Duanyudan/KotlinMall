package com.example.ordercenter.ui.activity

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.bigkoo.alertview.AlertView
import com.bigkoo.alertview.OnItemClickListener
import com.eightbitlab.rxbus.Bus
import com.example.baselibrary.ext.onClick
import com.example.baselibrary.ext.startLoading
import com.example.baselibrary.ui.activity.BaseMvpActivity
import com.example.ordercenter.R
import com.example.ordercenter.injection.component.DaggerShipAddressComponent
import com.example.ordercenter.injection.module.ShipAddressModule
import com.example.ordercenter.presenter.ShipAddressPresenter
import com.example.ordercenter.presenter.view.ShipAddressView
import com.kennyc.view.MultiStateView
import com.kotlin.base.ui.adapter.BaseRecyclerViewAdapter
import com.kotlin.order.common.OrderConstant
import com.kotlin.order.data.protocol.ShipAddress
import com.kotlin.order.event.SelectAddressEvent
import com.kotlin.order.ui.adapter.ShipAddressAdapter
import kotlinx.android.synthetic.main.activity_address.*
import org.jetbrains.anko.startActivity

/**
 * Created by user on 2018/3/29.
 */
class ShipAddressActivity : BaseMvpActivity<ShipAddressPresenter>(), ShipAddressView {
    private lateinit var mAdapter: ShipAddressAdapter
    override fun injectComponent() {
        DaggerShipAddressComponent.builder().activityComponent(acitivtyComponent)
                .shipAddressModule(ShipAddressModule())
                .build()
                .inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_address)
        initView()
    }

    override fun onStart() {
        super.onStart()
        loadData()
    }

    private fun initView() {
        mAddAddressBtn.onClick {
            startActivity<ShipAddressEditActivity>()
        }
        mAddressRv.layoutManager = LinearLayoutManager(this)
        mAdapter = ShipAddressAdapter(this)
        mAddressRv.adapter = mAdapter
        mAdapter.mOptClickListener = object : ShipAddressAdapter.OnOptClickListener {
            override fun onSetDefault(address: ShipAddress) {
                mPresenter.setDefaultShipAddress(address.id, address.shipUserName, address.shipUserMobile,
                        address.shipAddress, address.shipIsDefault)
            }

            override fun onEdit(address: ShipAddress) {
                startActivity<ShipAddressEditActivity>(OrderConstant.KEY_SHIP_ADDRESS to address)
            }

            override fun onDelete(address: ShipAddress) {
                showDeleteAlert(address)

            }
        }
        mAdapter.setOnItemClickListener(
                object : BaseRecyclerViewAdapter.OnItemClickListener<ShipAddress> {
                    override fun onItemClick(item: ShipAddress, position: Int) {
                        Bus.send(SelectAddressEvent(item))
                        finish()
                    }
                })
    }

    fun showDeleteAlert(address: ShipAddress) {
        AlertView("删除", "确定删除该地址？", "取消", null, arrayOf("确定"),
                this@ShipAddressActivity, AlertView.Style.Alert,
                object : OnItemClickListener {
                    override fun onItemClick(o: Any?, position: Int) {
                        if (position == 0) {
                            mPresenter.deleteShipAddress(address.id)
                        }
                    }
                }).show()
    }

    private fun loadData() {
        mMultiStateView.startLoading()
        mPresenter.getShipAddressList()
    }

    override fun onGetShipAddressList(result: MutableList<ShipAddress>?) {
        if (result != null && result.size > 0) {
            mAdapter.setData(result)
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_CONTENT
        } else {
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_EMPTY
        }
    }

    override fun onSetDefultResult(result: String) {
        loadData()
    }

    override fun onDeleteResult(result: String) {
        loadData()
    }
}