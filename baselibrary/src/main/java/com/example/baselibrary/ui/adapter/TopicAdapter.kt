package com.kotlin.mall.ui.adapter

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.baselibrary.R
import com.example.baselibrary.utils.GlideUtils
import kotlinx.android.synthetic.main.layout_topic_item.view.*

/*
    话题数据
 */
class TopicAdapter(private val context: Context, private val list: List<String>) : PagerAdapter() {
    override fun isViewFromObject(view: View?, `object`: Any?): Boolean {
        return view === `object`
    }

    override fun getCount(): Int {
        return this.list.size
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any?) {
        container.removeView(`object` as View)
    }

    override fun instantiateItem(parent: ViewGroup, position: Int): Any {
        val rooView = LayoutInflater.from(this.context).inflate(R.layout.layout_topic_item, null)
        GlideUtils.loadUrlImage(context, list[position], rooView.mTopicIv)
        parent.addView(rooView)
        return rooView
    }

}
