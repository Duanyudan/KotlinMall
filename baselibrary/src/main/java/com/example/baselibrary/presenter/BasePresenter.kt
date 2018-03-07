package com.example.baselibrary.presenter

import android.widget.Toast
import com.example.baselibrary.presenter.view.BaseView

/**
 * Created by Administrator on 2018/3/7.
 */
open class BasePresenter<T: BaseView>{
    lateinit var mView:T
}