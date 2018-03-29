package com.example.administrator.kotlinmall.ui.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.administrator.kotlinmall.R
import com.example.administrator.kotlinmall.ui.activity.SettingActivity
import com.example.administrator.kotlinmall.ui.adapter.HomeDiscountAdapter
import com.example.baselibrary.common.BaseConstant
import com.example.baselibrary.ext.loadUrl
import com.example.baselibrary.ext.onClick
import com.example.baselibrary.ui.fragment.BaseFragment
import com.example.baselibrary.utils.AppPrefsUtils
import com.example.baselibrary.widgets.BannerImageLoader
import com.example.ordercenter.ui.activity.OrderActivity
import com.example.ordercenter.ui.activity.ShipAddressActivity
import com.example.provider.common.ProviderConstant
import com.example.provider.common.afterLogin
import com.example.provider.common.isLogined
import com.example.usercenter.ui.activity.UserInfoActivity
import com.kotlin.mall.common.*
import com.kotlin.mall.ui.adapter.TopicAdapter
import com.kotlin.order.common.OrderConstant
import com.kotlin.order.common.OrderStatus
import com.youth.banner.BannerConfig
import com.youth.banner.Transformer
import kotlinx.android.synthetic.main.fragment_me.*
import me.crosswall.lib.coverflow.CoverFlow
import org.jetbrains.anko.support.v4.startActivity

/**
 * Created by user on 2018/3/19.
 */
class MeFragment : BaseFragment(), View.OnClickListener {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_me, null)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {

        mUserIconIv.onClick(this)
        mUserNameTv.onClick(this)
        mAddressTv.onClick { this }
        mSettingTv.onClick(this)
        mAllOrderTv.onClick { this }
        mWaitPayOrderTv.onClick { this }
        mWaitConfirmOrderTv.onClick { this }
        mCompleteOrderTv.onClick { this }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.mUserIconIv, R.id.mUserNameTv -> {
                afterLogin {
                    startActivity<UserInfoActivity>()
                }
            }
            R.id.mSettingTv -> {
                startActivity<SettingActivity>()
            }
            R.id.mAddressTv -> {
                startActivity<ShipAddressActivity>()
            }
            R.id.mAllOrderTv -> {
                afterLogin {
                    startActivity<OrderActivity>()
                }
            }
            R.id.mWaitPayOrderTv -> {
                startActivity<OrderActivity>(OrderConstant.KEY_ORDER_STATUS to OrderStatus.ORDER_WAIT_PAY)
            }
            R.id.mWaitConfirmOrderTv -> {
                startActivity<OrderActivity>(OrderConstant.KEY_ORDER_STATUS to OrderStatus.ORDER_WAIT_CONFIRM)
            }
            R.id.mCompleteOrderTv -> {
                startActivity<OrderActivity>(OrderConstant.KEY_ORDER_STATUS to OrderStatus.ORDER_COMPLETED)
            }
        }
    }


    override fun onStart() {
        super.onStart()
        loadData()
    }

    private fun loadData() {
        if (!isLogined()) {
            mUserIconIv.setImageResource(R.drawable.icon_default_user)
            mUserNameTv.text = getString(R.string.un_login_text)
        } else {
            val userIcon = AppPrefsUtils.getString(ProviderConstant.KEY_SP_USER_ICON)
            if (userIcon.isNotEmpty()) {
                mUserIconIv.loadUrl(userIcon)
            }
            mUserNameTv.text = AppPrefsUtils.getString(ProviderConstant.KEY_SP_USER_NAME)
        }
    }
}