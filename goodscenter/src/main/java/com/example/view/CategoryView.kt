package com.example.view

import com.example.baselibrary.data.protocol.UserInfo
import com.example.baselibrary.presenter.view.BaseView
import com.example.data.protocol.Category

/**
 * Created by Administrator on 2018/3/7.
 */
interface CategoryView : BaseView {
    fun onGetCategoryResult(result: MutableList<Category>?)
}