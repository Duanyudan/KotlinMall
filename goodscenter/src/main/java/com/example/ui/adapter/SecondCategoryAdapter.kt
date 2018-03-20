package com.example.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.baselibrary.ext.loadUrl
import com.example.data.protocol.Category
import com.example.goodscenter.R
import com.kotlin.base.ui.adapter.BaseRecyclerViewAdapter
import kotlinx.android.synthetic.main.layout_second_category_item.view.*

/**
 * Created by user on 2018/3/20.
 */
class SecondCategoryAdapter(context: Context) : BaseRecyclerViewAdapter<Category, SecondCategoryAdapter.ViewHolder>(context) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.layout_second_category_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        val bean = dataList[position]
        holder.itemView.mCategoryIconIv.loadUrl(bean.categoryIcon)
        holder.itemView.mSecondCategoryNameTv.text = bean.categoryName

    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    }
}