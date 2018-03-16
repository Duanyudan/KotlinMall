package com.example.baselibrary.ui.activity

import android.os.Bundle
import com.example.baselibrary.common.BaseApplication
import com.example.baselibrary.component.ActivityComponent
import com.example.baselibrary.component.DaggerActivityComponent
import com.example.baselibrary.module.ActivityModule
import com.example.baselibrary.module.LifecycleProvidersModule
import com.example.baselibrary.presenter.BasePresenter
import com.example.baselibrary.presenter.view.BaseView
import org.jetbrains.anko.toast
import javax.inject.Inject

/**
 * Created by Administrator on 2018/3/7.
 */
open abstract class BaseMvpActivity<T : BasePresenter<*>> : BaseActivity(), BaseView {
    override fun hideLoading() {
    }

    override fun onError(msg:String) {
        toast(msg)
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
                .appComponent((application as BaseApplication).appComponent)
                .activityModule(ActivityModule(this))
                .lifecycleProvidersModule(LifecycleProvidersModule(this))
                .build()
    }

    abstract fun injectComponent()
}