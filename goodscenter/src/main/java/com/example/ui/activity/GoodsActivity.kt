package com.example.ui.activity

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder
import cn.bingoogolapple.refreshlayout.BGARefreshLayout
import com.example.baselibrary.ext.startLoading
import com.example.baselibrary.ui.activity.BaseMvpActivity
import com.example.common.GoodsConstant.Companion.KEY_CATEGORY_ID
import com.example.common.GoodsConstant.Companion.KEY_GOODS_KEYWORD
import com.example.common.GoodsConstant.Companion.KEY_SEARCH_GOODS_TYPE
import com.example.data.protocol.Goods
import com.example.goodscenter.R
import com.example.injection.component.DaggerGoodsComponent
import com.example.injection.module.GoodsModule
import com.example.presenter.GoodsPresenter
import com.example.view.GoodsView
import com.kennyc.view.MultiStateView
import com.kotlin.goods.ui.adapter.GoodsAdapter
import kotlinx.android.synthetic.main.activity_goods.*


/**
 * Created by user on 2018/3/23.
 */
class GoodsActivity : BaseMvpActivity<GoodsPresenter>(), GoodsView, BGARefreshLayout.BGARefreshLayoutDelegate {
    private lateinit var mAdapter: GoodsAdapter
    private var mCurrentPage: Int = 1
    private var mMaxPage: Int = 1
    private var result: MutableList<Goods>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_goods)
        initView()
        initRefreshLayout()
        loadData()
    }

    private fun initRefreshLayout() {
        mRefreshLayout.setDelegate(this)
        val viewHolder = BGANormalRefreshViewHolder(this, true)
        viewHolder.setLoadMoreBackgroundColorRes(R.color.common_bg)
        viewHolder.setRefreshViewBackgroundColorRes(R.color.common_bg)
        mRefreshLayout.setRefreshViewHolder(viewHolder)
    }

    private fun loadData() {
        if (intent.getIntExtra(KEY_SEARCH_GOODS_TYPE, 0) != 0) {
            mMultiStateView.startLoading()
            mPresenter.getGoodsListByKeyword(intent.getStringExtra(KEY_GOODS_KEYWORD), mCurrentPage)
        } else {
            mMultiStateView.startLoading()
            mPresenter.getGoodsList(intent.getIntExtra(KEY_CATEGORY_ID, 1), mCurrentPage)
        }
    }

    private fun initView() {
        mGoodsRv.layoutManager = GridLayoutManager(this, 2)
        mAdapter = GoodsAdapter(this)
        mGoodsRv.adapter = mAdapter
    }

    override fun onBGARefreshLayoutBeginLoadingMore(refreshLayout: BGARefreshLayout?): Boolean {
        if (mCurrentPage < mMaxPage) {
            mCurrentPage++
            loadData()
            return true
        } else {
            return false
        }

    }

    override fun onBGARefreshLayoutBeginRefreshing(refreshLayout: BGARefreshLayout?) {
        mCurrentPage = 1
        loadData()
    }

    override fun onGetGoodsList(result: MutableList<Goods>?) {
        mRefreshLayout.endLoadingMore()
        mRefreshLayout.endRefreshing()
        if (result != null && result.size > 0) {
            result?.let {
                mMaxPage = result[0].maxPage
                if (mCurrentPage == 1) {
                    mAdapter.setData(result)
                } else {
                    mAdapter.dataList.addAll(result)
                    mAdapter.notifyDataSetChanged()
                }
                mMultiStateView.viewState = MultiStateView.VIEW_STATE_CONTENT
            }
        } else {
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_EMPTY
        }
    }

    override fun onGetGoodsDetail(result: MutableList<Goods>?) {
    }

    override fun injectComponent() {
        DaggerGoodsComponent.builder()
                .activityComponent(acitivtyComponent)
                .goodsModule(GoodsModule())
                .build()
                .inject(this)
        mPresenter.mView = this
    }
}