package com.example.baselibrary.presenter.view

/**
 * Created by Administrator on 2018/3/7.
 */

 interface BaseView{
    fun showLoading()
    fun hideLoading()
    fun onError(msg:String)
}
