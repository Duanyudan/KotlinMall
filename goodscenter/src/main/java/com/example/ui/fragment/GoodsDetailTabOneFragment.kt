package com.example.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.eightbitlab.rxbus.Bus
import com.example.baselibrary.ui.fragment.BaseMvpFragment
import com.example.baselibrary.utils.YuanFenConverter
import com.example.baselibrary.widgets.BannerImageLoader
import com.example.common.GoodsConstant
import com.example.data.protocol.Goods
import com.example.event.GoodsDetailEvent
import com.example.goodscenter.R
import com.example.injection.component.DaggerGoodsComponent
import com.example.injection.module.GoodsModule
import com.example.presenter.GoodsDetailPresenter
import com.example.view.GoodsDetailView
import com.youth.banner.BannerConfig
import com.youth.banner.Transformer
import kotlinx.android.synthetic.main.fragment_goods_detail_tab_one.*

/**
 * Created by Administrator on 2018/3/23.
 */
class GoodsDetailTabOneFragment : BaseMvpFragment<GoodsDetailPresenter>(),
        GoodsDetailView {
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater?.inflate(R.layout.fragment_goods_detail_tab_one, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        loadData()

    }

    private fun initView() {
        mGoodsDetailBanner.setImageLoader(BannerImageLoader())
//        动画效果
        mGoodsDetailBanner.setBannerAnimation(Transformer.Accordion)
                .isAutoPlay(true)
//                指示器位置
                .setIndicatorGravity(BannerConfig.RIGHT)
//        延时
                .setDelayTime(2000)

    }

    private fun loadData() {
        mPresenter.getGoodsDetail(activity.intent.getIntExtra(GoodsConstant.KEY_GOODS_ID, -1))
    }

    override fun onGetGoodsDetail(result: Goods) {
        mGoodsDetailBanner.setImages(result.goodsBanner.split(","))
                .start()
        mGoodsDescTv.text=result.goodsDesc
        mGoodsPriceTv.text=YuanFenConverter.changeF2YWithUnit(result.goodsDefaultPrice)
        mSkuSelectedTv.text=result.goodsDefaultSku
        Bus.send(GoodsDetailEvent(result.goodsDetailOne,result.goodsDetailTwo))
    }

    override fun injectComponent() {
        DaggerGoodsComponent.builder().activityComponent(acitivtyComponent)
                .goodsModule(GoodsModule())
                .build()
                .inject(this)
        mPresenter.mView = this

    }
}