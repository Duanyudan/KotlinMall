package com.example.baselibrary.ui.activity

import com.example.baselibrary.presenter.BasePresenter
import com.example.baselibrary.presenter.view.BaseView

/**
 * Created by Administrator on 2018/3/7.
 */
open class BaseMvpActivity<T : BasePresenter<*>> : BaseActivity(), BaseView {
    override fun hideLoading() {
    }

    override fun onError() {
    }

    override fun showLoading() {
    }

    lateinit var mPresenter: T
}