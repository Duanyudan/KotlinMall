package com.example.baselibrary.common

import android.app.Application
import com.example.baselibrary.component.AppComponent
import com.example.baselibrary.component.DaggerAppComponent
import com.example.baselibrary.module.AppModule

/**
 * Created by user on 2018/3/15.
 */
 class BaseApplication: Application() {
    lateinit var appComponent: AppComponent
    override fun onCreate() {
        super.onCreate()
        initAppInjection()
    }

    private fun initAppInjection() {
        appComponent= DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }

}