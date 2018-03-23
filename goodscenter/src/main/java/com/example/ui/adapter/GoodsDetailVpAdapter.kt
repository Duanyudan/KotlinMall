package com.example.ui.adapter

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.example.ui.fragment.GoodsDetailTabOneFragment
import com.example.ui.fragment.GoodsDetailTabTwoFragment

/**
 * Created by Administrator on 2018/3/23.
 */
class GoodsDetailVpAdapter(fm:FragmentManager,context: Context):FragmentPagerAdapter(fm) {
    private val titles= arrayOf("商品","详情")
    override fun getItem(position: Int): Fragment {
        if (position!=0){
            return GoodsDetailTabOneFragment()
        }else{
            return GoodsDetailTabTwoFragment()
        }

    }

    override fun getCount(): Int {
        return titles.size
}

    override fun getPageTitle(position: Int): CharSequence {
        return titles[position]
    }
}