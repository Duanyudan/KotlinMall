package com.example.baselibrary.common

import android.app.Application
import android.content.Context
import com.example.baselibrary.component.AppComponent
import com.example.baselibrary.component.DaggerAppComponent
import com.example.baselibrary.module.AppModule

/**
 * Created by user on 2018/3/15.
 */
/*
    Application 基类
 */
open class BaseApplication : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        initAppInjection()

        context = this

    }

    /*
        Application Component初始化
     */
    private fun initAppInjection() {
        appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }

    /*
        全局伴生对象
     */
    companion object {
        lateinit var context: Context
    }
}