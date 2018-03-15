package com.example.baselibrary.presenter

import android.widget.Toast
import com.example.baselibrary.presenter.view.BaseView
import com.trello.rxlifecycle.LifecycleProvider
import javax.inject.Inject

/**
 * Created by Administrator on 2018/3/7.
 */
open class BasePresenter<T: BaseView>{
    lateinit var mView:T

    @Inject
    lateinit var lifecycleProvider: LifecycleProvider<*>
}