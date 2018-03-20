package com.example.administrator.kotlinmall.ui.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.administrator.kotlinmall.R
import com.example.administrator.kotlinmall.ui.adapter.HomeDiscountAdapter
import com.example.baselibrary.ui.fragment.BaseFragment
import com.example.baselibrary.widgets.BannerImageLoader
import com.kotlin.mall.common.*
import com.kotlin.mall.ui.adapter.TopicAdapter
import com.youth.banner.BannerConfig
import com.youth.banner.Transformer
import kotlinx.android.synthetic.main.fragment_home.*
import me.crosswall.lib.coverflow.CoverFlow

/**
 * Created by user on 2018/3/19.
 */
class HomeFragment : BaseFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_home, null)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initBanner()
        initNews()
        initDiscount()
        initTopic()
    }

    private fun initNews() {
//公告
        mNewsFlipperView.setData(arrayOf("夏日炎炎", "新用户立领1000元优惠券"))
    }

    private fun initBanner() {
        mHomeBanner.setImageLoader(BannerImageLoader())
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

    private fun initDiscount() {
        val manager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        val discountAdapter = HomeDiscountAdapter(activity)
        mHomeDiscountRv.layoutManager = manager
        mHomeDiscountRv.adapter = discountAdapter
        discountAdapter.setData(mutableListOf(HOME_DISCOUNT_ONE, HOME_DISCOUNT_TWO,
                HOME_DISCOUNT_THREE, HOME_DISCOUNT_FOUR, HOME_DISCOUNT_FIVE))
    }

    private fun initTopic() {
        mTopicPager.adapter = TopicAdapter(context, listOf(HOME_TOPIC_ONE, HOME_TOPIC_TWO, HOME_TOPIC_THREE,
                HOME_TOPIC_FOUR, HOME_TOPIC_FIVE))
        mTopicPager.currentItem = 1
        mTopicPager.offscreenPageLimit = 5

        CoverFlow.Builder().with(mTopicPager).scale(0.3f).pagerMargin(-30.0f)
                .spaceSize(0.0f).build()
    }
}