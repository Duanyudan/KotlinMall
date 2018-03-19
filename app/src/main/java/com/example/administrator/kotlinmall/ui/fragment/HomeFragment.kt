package com.example.administrator.kotlinmall.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.administrator.kotlinmall.R
import com.example.baselibrary.ui.fragment.BaseFragment
import com.example.baselibrary.widgets.BannerImageLoader
import com.kotlin.mall.common.HOME_BANNER_FOUR
import com.kotlin.mall.common.HOME_BANNER_ONE
import com.kotlin.mall.common.HOME_BANNER_THREE
import com.kotlin.mall.common.HOME_BANNER_TWO
import com.youth.banner.BannerConfig
import com.youth.banner.Transformer
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*

/**
 * Created by user on 2018/3/19.
 */
class HomeFragment : BaseFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val rootView=inflater.inflate(R.layout.fragment_home, null)
        initBanner(rootView)
        return rootView
    }

    private fun initBanner(view: View) {
        view.mHomeBanner.setImageLoader(BannerImageLoader())
//        图片集
        mHomeBanner.setImages(listOf(HOME_BANNER_ONE, HOME_BANNER_TWO,
                HOME_BANNER_THREE, HOME_BANNER_FOUR))
//        动画效果
        mHomeBanner.setBannerAnimation(Transformer.Accordion)
                .isAutoPlay(true)
//                指示器位置
                .setIndicatorGravity(BannerConfig.RIGHT)
//        延时
                .setDelayTime(2000)
                .start()
    }
}