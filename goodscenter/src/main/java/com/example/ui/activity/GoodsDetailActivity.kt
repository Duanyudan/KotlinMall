package com.example.ui.activity

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.view.Gravity
import android.view.View
import com.eightbitlab.rxbus.Bus
import com.eightbitlab.rxbus.registerInBus
import com.example.baselibrary.ext.onClick
import com.example.baselibrary.ui.activity.BaseActivity
import com.example.baselibrary.utils.AppPrefsUtils
import com.example.common.GoodsConstant
import com.example.goodscenter.R
import com.example.provider.common.afterLogin
import com.example.ui.adapter.GoodsDetailVpAdapter
import com.kotlin.goods.event.AddCartEvent
import com.kotlin.goods.event.UpdateCartSizeEvent
import kotlinx.android.synthetic.main.activity_goods_detail.*
import org.jetbrains.anko.startActivity
import q.rorbin.badgeview.QBadgeView

/**
 * Created by Administrator on 2018/3/23.
 */
class GoodsDetailActivity : BaseActivity() {
    private lateinit var mCartBadge: QBadgeView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_goods_detail)

        initView()
        initOvserve()
        loadCartSize()
    }

    private fun loadCartSize() {
        setCartBadge()
    }

    private fun initView() {
        mGoodsDetailTab.tabMode = TabLayout.MODE_FIXED
        mGoodsDetailVp.adapter = GoodsDetailVpAdapter(supportFragmentManager, this)
        mGoodsDetailTab.setupWithViewPager(mGoodsDetailVp)

        mAddCartBtn.onClick {
            afterLogin { Bus.send(AddCartEvent()) }
        }

        mEnterCartTv.onClick {
            startActivity<CartActivity>()
        }

        mCartBadge = QBadgeView(this)

        mLeftIv.onClick {
            finish()
        }
    }

    fun getRootView(): View {
        return mRootView
    }

    private fun initOvserve() {
        Bus.observe<UpdateCartSizeEvent>()
                .subscribe { setCartBadge() }
                .registerInBus(this)

    }

    private fun setCartBadge() {
        mCartBadge.badgeGravity = Gravity.END or Gravity.TOP
        mCartBadge.setGravityOffset(22f, -2f, true)
        mCartBadge.setBadgeTextSize(6f, true)
        mCartBadge.bindTarget(mEnterCartTv).badgeNumber = AppPrefsUtils.getInt(GoodsConstant.SP_CART_SIZE)
    }

    override fun onDestroy() {
        super.onDestroy()
        Bus.unregister(this)
    }
}