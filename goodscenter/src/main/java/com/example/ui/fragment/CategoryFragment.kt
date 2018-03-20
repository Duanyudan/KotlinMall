package com.example.ui.fragment

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SimpleCursorAdapter
import com.example.baselibrary.ui.fragment.BaseFragment
import com.example.baselibrary.ui.fragment.BaseMvpFragment
import com.example.data.protocol.Category
import com.example.goodscenter.R
import com.example.injection.component.DaggerCategoryComponent
import com.example.injection.module.CategoryModule
import com.example.presenter.CategoryPresenter
import com.example.ui.adapter.SecondCategoryAdapter
import com.example.ui.adapter.TopCategoryAdapter
import com.example.view.CategoryView
import com.kennyc.view.MultiStateView
import com.kotlin.base.ui.adapter.BaseRecyclerViewAdapter
import com.kotlin.mall.ui.adapter.TopicAdapter
import kotlinx.android.synthetic.main.fragment_category.*

/**
 * Created by Administrator on 2018/3/20.
 */
class CategoryFragment : BaseMvpFragment<CategoryPresenter>(), CategoryView {
    lateinit var topAdapter: TopCategoryAdapter
    lateinit var secondAdapter: SecondCategoryAdapter
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
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
        topAdapter.setOnItemClickListener(object : BaseRecyclerViewAdapter.OnItemClickListener<Category> {
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
        secondAdapter.setOnItemClickListener(object : BaseRecyclerViewAdapter.OnItemClickListener<Category> {
            override fun onItemClick(item: Category, position: Int) {

            }
        })
    }

    private fun loadData(parentId:Int=0) {
        mPresenter.getCategory(parentId)

    }

    override fun onGetCategoryResult(result: MutableList<Category>?) {
        result?.let {
            if (result[0].parentId == 0) {
                topAdapter.setData(result)
                mPresenter.getCategory(result[0].id)
                result[0].isSelected=true
            } else {
                secondAdapter.setData(result)
                mMultiStateView.viewState=MultiStateView.VIEW_STATE_CONTENT
            }
        }
    }

    override fun injectComponent() {
        DaggerCategoryComponent.builder()
                .activityComponent(acitivtyComponent).categoryModule(CategoryModule())
                .build().inject(this)
        mPresenter.mView = this
    }
}