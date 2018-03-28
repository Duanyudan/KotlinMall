package com.example.baselibrary.ui.activity

import android.os.Bundle
import com.alibaba.android.arouter.launcher.ARouter
import com.example.baselibrary.common.BaseApplication
import com.example.baselibrary.component.ActivityComponent
import com.example.baselibrary.component.DaggerActivityComponent
import com.example.baselibrary.module.ActivityModule
import com.example.baselibrary.module.LifecycleProvidersModule
import com.example.baselibrary.presenter.BasePresenter
import com.example.baselibrary.presenter.view.BaseView
import com.example.baselibrary.widget.ProgressLoading
import org.jetbrains.anko.toast
import javax.inject.Inject

/**
 * Created by Administrator on 2018/3/7.
 */
 abstract class BaseMvpActivity<T : BasePresenter<*>> : BaseActivity(), BaseView {
    private lateinit var mLoadingDialog:ProgressLoading

    @Inject
    lateinit var mPresenter: T
    lateinit var acitivtyComponent: ActivityComponent
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initActivityInjection()
        injectComponent()
       mLoadingDialog= ProgressLoading.create(this)
        ARouter.getInstance().inject(this)
    }

    private fun initActivityInjection() {
        acitivtyComponent = DaggerActivityComponent.builder()
                .appComponent((application as BaseApplication).appComponent)
                .activityModule(ActivityModule(this))
                .lifecycleProvidersModule(LifecycleProvidersModule(this))
                .build()
    }

    abstract fun injectComponent()


    /*
        显示加载框，默认实现
     */
    override fun showLoading() {
        mLoadingDialog.showLoading()
    }

    /*
        隐藏加载框，默认实现
     */
    override fun hideLoading() {
        mLoadingDialog.hideLoading()
    }

    /*
        错误信息提示，默认实现
     */
    override fun onError(text:String) {
        toast(text)
    }
}