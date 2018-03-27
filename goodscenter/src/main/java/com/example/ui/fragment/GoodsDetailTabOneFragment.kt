package com.example.ui.fragment

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import com.eightbitlab.rxbus.Bus
import com.eightbitlab.rxbus.registerInBus
import com.example.baselibrary.ext.onClick
import com.example.baselibrary.ui.activity.BaseActivity
import com.example.baselibrary.ui.fragment.BaseMvpFragment
import com.example.baselibrary.utils.YuanFenConverter
import com.example.baselibrary.widgets.BannerImageLoader
import com.example.common.GoodsConstant
import com.example.data.protocol.AddCartReq
import com.example.data.protocol.Goods
import com.example.event.GoodsDetailEvent
import com.example.goodscenter.R
import com.example.injection.component.DaggerGoodsComponent
import com.example.injection.module.GoodsModule
import com.example.presenter.GoodsDetailPresenter
import com.example.ui.activity.GoodsDetailActivity
import com.example.view.GoodsDetailView
import com.kotlin.goods.event.AddCartEvent
import com.kotlin.goods.event.SkuChangedEvent
import com.kotlin.goods.event.UpdateCartSizeEvent
import com.kotlin.goods.widget.GoodsSkuPopView
import com.youth.banner.BannerConfig
import com.youth.banner.Transformer
import kotlinx.android.synthetic.main.fragment_goods_detail_tab_one.*

/**
 * Created by Administrator on 2018/3/23.
 */
class GoodsDetailTabOneFragment : BaseMvpFragment<GoodsDetailPresenter>(),
        GoodsDetailView {
    private lateinit var mSkuPop: GoodsSkuPopView
    private lateinit var mAnimationStart: ScaleAnimation
    private lateinit var mAnimationEnd: ScaleAnimation

    private var mCurGoods: Goods? = null
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater?.inflate(R.layout.fragment_goods_detail_tab_one, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initAnimation()
        initSkuPop()
        loadData()
        initOvserve()
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

        mSkuView.onClick {
            mSkuPop.showAtLocation((activity as GoodsDetailActivity).contentView,
                    Gravity.BOTTOM and Gravity.CENTER_HORIZONTAL, 0, 0)
            (activity as BaseActivity).contentView.startAnimation(mAnimationStart)
        }
    }

    private fun loadData() {
        mPresenter.getGoodsDetail(activity.intent.getIntExtra(GoodsConstant.KEY_GOODS_ID, -1))
    }

    private fun loadPopData(result: Goods) {
        mSkuPop.setGoodsIcon(result.goodsDefaultIcon)
        mSkuPop.setGoodsCode(result.goodsCode)
        mSkuPop.setGoodsPrice(result.goodsDefaultPrice)

        mSkuPop.setSkuData(result.goodsSku)
    }

    private fun initSkuPop() {
        mSkuPop = GoodsSkuPopView(activity)
        mSkuPop.setOnDismissListener {
            (activity as BaseActivity).contentView.startAnimation(mAnimationEnd)
        }
    }

    private fun initAnimation() {
        mAnimationStart = ScaleAnimation(1f, 0.95f, 1f, 0.95f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
        mAnimationStart.duration = 500
        mAnimationStart.fillAfter = true

        mAnimationEnd = ScaleAnimation(0.95f, 1f, 0.95f, 1f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
        mAnimationStart.duration = 500
        mAnimationStart.fillAfter = true
    }

    override fun onGetGoodsDetail(result: Goods) {
        mCurGoods = result
        mGoodsDetailBanner.setImages(result.goodsBanner.split(","))
                .start()
        mGoodsDescTv.text = result.goodsDesc
        mGoodsPriceTv.text = YuanFenConverter.changeF2YWithUnit(result.goodsDefaultPrice)
        mSkuSelectedTv.text = result.goodsDefaultSku
        Bus.send(GoodsDetailEvent(result.goodsDetailOne, result.goodsDetailTwo))
        loadPopData(result)
    }

    override fun onAddCartResult(result: Int) {
        Bus.send(UpdateCartSizeEvent())
    }

    private fun addCart() {
        mCurGoods?.let {
            var req: AddCartReq
            req = AddCartReq(it.id,
                    it.goodsDesc,
                    it.goodsDefaultIcon,
                    it.goodsDefaultPrice,
                    mSkuPop.getSelectCount(),
                    mSkuPop.getSelectSku())
            mPresenter.addCart(req)
        }

    }

    private fun initOvserve() {
        Bus.observe<SkuChangedEvent>()
                .subscribe {
                    mSkuSelectedTv.text = mSkuPop.getSelectSku() + GoodsConstant.SKU_SEPARATOR + mSkuPop.getSelectCount() + "件"
                }.registerInBus(this)
        Bus.observe<AddCartEvent>()
                .subscribe { addCart() }
                .registerInBus(this)
        Bus.observe<UpdateCartSizeEvent>()
                .subscribe {  }
                .registerInBus(this)

    }

    override fun injectComponent() {
        DaggerGoodsComponent.builder().activityComponent(acitivtyComponent)
                .goodsModule(GoodsModule())
                .build()
                .inject(this)
        mPresenter.mView = this

    }

    override fun onDestroy() {
        super.onDestroy()
        Bus.unregister(this)
    }
}