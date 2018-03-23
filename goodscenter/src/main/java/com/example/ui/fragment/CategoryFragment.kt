package com.example.ui.fragment

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.baselibrary.ext.startLoading
import com.example.baselibrary.ui.fragment.BaseMvpFragment
import com.example.common.GoodsConstant.Companion.KEY_CATEGORY_ID
import com.example.data.protocol.Category
import com.example.goodscenter.R
import com.example.injection.component.DaggerCategoryComponent
import com.example.injection.module.CategoryModule
import com.example.presenter.CategoryPresenter
import com.example.ui.activity.GoodsActivity
import com.example.ui.adapter.SecondCategoryAdapter
import com.example.ui.adapter.TopCategoryAdapter
import com.example.view.CategoryView
import com.kennyc.view.MultiStateView
import com.kotlin.base.ui.adapter.BaseRecyclerViewAdapter
import kotlinx.android.synthetic.main.fragment_category.*
import org.jetbrains.anko.support.v4.startActivity

/**
 * Created by Administrator on 2018/3/20.
 */
class CategoryFragment : BaseMvpFragment<CategoryPresenter>(), CategoryView {
    lateinit var topAdapter: TopCategoryAdapter
    lateinit var secondAdapter: SecondCategoryAdapter
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater?.inflate(R.layout.fragment_category, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        loadData()
    }

    private fun initView() {
        mTopCategoryRv.layoutManager = LinearLayoutManager(context)
        topAdapter = TopCategoryAdapter(context)
        mTopCategoryRv.adapter = topAdapter
        topAdapter.setOnItemClickListener(
                object : BaseRecyclerViewAdapter.OnItemClickListener<Category> {
                    override fun onItemClick(item: Category, position: Int) {
                        for (category in topAdapter.dataList) {
                            category.isSelected = item.id == category.id
                        }
                        topAdapter.notifyDataSetChanged()
                        loadData(item.id)
                    }
                })

        mSecondCategoryRv.layoutManager = GridLayoutManager(context, 3)
        secondAdapter = SecondCategoryAdapter(context)
        mSecondCategoryRv.adapter = secondAdapter
        secondAdapter.setOnItemClickListener(
                object : BaseRecyclerViewAdapter.OnItemClickListener<Category> {
                    override fun onItemClick(item: Category, position: Int) {
                        startActivity<GoodsActivity>(KEY_CATEGORY_ID to item.id)
                    }
                })
    }

    private fun loadData(parentId: Int = 0) {
        if (parentId != 0) {
            mMultiStateView.startLoading()
        }
    }

    override fun onGetCategoryResult(result: MutableList<Category>?) {
        if (result != null && result.size > 0) {
            result?.let {
                if (result[0].parentId == 0) {
                    topAdapter.setData(result)
                    mPresenter.getCategory(result[0].id)
                    result[0].isSelected = true
                } else {
                    secondAdapter.setData(result)
                    mTopCategoryIv.visibility = View.VISIBLE
                    mCategoryTitleTv.visibility = View.VISIBLE
                    mMultiStateView.viewState = MultiStateView.VIEW_STATE_CONTENT
                }
            }
        } else {
            mTopCategoryIv.visibility = View.GONE
            mCategoryTitleTv.visibility = View.GONE
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_EMPTY

        }

    }

    override fun injectComponent() {
        DaggerCategoryComponent.builder()
                .activityComponent(acitivtyComponent).categoryModule(CategoryModule())
                .build().inject(this)
        mPresenter.mView = this
    }
}