package com.example.ordercenter.ui.activity

import android.os.Bundle
import com.example.baselibrary.ext.onClick
import com.example.baselibrary.ui.activity.BaseMvpActivity
import com.example.ordercenter.R
import com.example.ordercenter.injection.component.DaggerShipAddressComponent
import com.example.ordercenter.injection.module.ShipAddressModule
import com.example.ordercenter.presenter.EditShipAddressPresenter
import com.example.ordercenter.presenter.view.EditShipAddressView
import com.kotlin.order.common.OrderConstant
import com.kotlin.order.data.protocol.ShipAddress
import kotlinx.android.synthetic.main.activity_edit_address.*
import org.jetbrains.anko.toast

/**
 * Created by user on 2018/3/29.
 */
class ShipAddressEditActivity : BaseMvpActivity<EditShipAddressPresenter>(), EditShipAddressView {
    private var mAddress: ShipAddress? = null

    override fun injectComponent() {
        DaggerShipAddressComponent.builder().activityComponent(acitivtyComponent)
                .shipAddressModule(ShipAddressModule())
                .build()
                .inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_address)
        initView()
        initData()
    }

    private fun initData() {
        mAddress = intent.getParcelableExtra(OrderConstant.KEY_SHIP_ADDRESS)
        mAddress?.let {
            mShipNameEt.setText(it.shipUserName)
            mShipMobileEt.setText(it.shipUserMobile)
            mShipAddressEt.setText(it.shipAddress)
        }
    }

    private fun initView() {
        mSaveBtn.onClick {
            if (mShipNameEt.text.isNullOrEmpty()) {
                return@onClick
            }
            if (mShipMobileEt.text.isNullOrEmpty()) {
                return@onClick
            }
            if (mShipAddressEt.text.isNullOrEmpty()) {
                return@onClick
            }
            if (mAddress == null) {
                mPresenter.addShipAddress(mShipNameEt.text.toString(),
                        mShipMobileEt.text.toString(),
                        mShipAddressEt.text.toString())
            } else {
                mAddress!!.shipUserName = mShipNameEt.text.toString()
                mAddress!!.shipUserMobile = mShipMobileEt.text.toString()
                mAddress!!.shipAddress = mShipAddressEt.text.toString()
                mPresenter.editShipAddress(mAddress!!)
            }
        }
    }

    override fun onAddAddressResult(result: String) {
        toast(result)
        finish()
    }

    override fun onEditAddressResult(result: String) {
        toast(result)
        finish()
    }
}