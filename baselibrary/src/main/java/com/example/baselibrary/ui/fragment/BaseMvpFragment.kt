package com.example.baselibrary.ui.fragment

import android.os.Bundle
import com.example.baselibrary.common.BaseApplication
import com.example.baselibrary.component.ActivityComponent
import com.example.baselibrary.component.DaggerActivityComponent
import com.example.baselibrary.module.ActivityModule
import com.example.baselibrary.module.LifecycleProvidersModule
import com.example.baselibrary.presenter.BasePresenter
import com.example.baselibrary.presenter.view.BaseView
import javax.inject.Inject

/**
 * Created by Administrator on 2018/3/7.
 */
open abstract class BaseMvpFragment<T : BasePresenter<*>> : BaseFragment(), BaseView {
    override fun hideLoading() {
    }

    override fun onError() {
    }

    override fun showLoading() {
    }

    @Inject
    lateinit var mPresenter: T
    lateinit var acitivtyComponent: ActivityComponent
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initActivityInjection()
        injectComponent()
    }

    private fun initActivityInjection() {
        acitivtyComponent = DaggerActivityComponent.builder()
                .appComponent((activity.application as BaseApplication).appComponent)
                .activityModule(ActivityModule(activity))
                .lifecycleProvidersModule(LifecycleProvidersModule(this))
                .build()
    }

    abstract fun injectComponent()
}