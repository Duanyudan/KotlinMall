package com.example.ui.activity

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.view.View
import com.alibaba.android.arouter.launcher.ARouter
import com.example.baselibrary.ext.onClick
import com.example.baselibrary.ui.activity.BaseActivity
import com.example.goodscenter.R
import com.example.provider.router.RouterPath
import com.example.ui.adapter.GoodsDetailVpAdapter
import kotlinx.android.synthetic.main.activity_goods_detail.*

/**
 * Created by Administrator on 2018/3/23.
 */
class GoodsDetailActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_goods_detail)

        initView()
    }

    private fun initView() {
        mGoodsDetailTab.tabMode=TabLayout.MODE_FIXED
        mGoodsDetailVp.adapter=GoodsDetailVpAdapter(supportFragmentManager,this)
        mGoodsDetailTab.setupWithViewPager(mGoodsDetailVp)

        mAddCartBtn.onClick {
            ARouter.getInstance().build(RouterPath.UserCenter.PATH_LOGIN).navigation()
        }
    }

    fun getRootView(): View {
        return mRootView
    }
}