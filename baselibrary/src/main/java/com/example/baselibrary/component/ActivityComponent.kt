package com.example.baselibrary.component

import android.app.Activity
import android.content.Context
import com.example.baselibrary.injection.ActivityScope
import com.example.baselibrary.module.ActivityModule
import com.example.baselibrary.module.LifecycleProvidersModule
import com.trello.rxlifecycle.LifecycleProvider
import dagger.Component

/**
 * Created by user on 2018/3/15.
 */
@ActivityScope
@Component(dependencies = arrayOf(AppComponent::class),
        modules = arrayOf(ActivityModule::class,LifecycleProvidersModule::class))
interface ActivityComponent {
    fun activity(): Activity

    fun context():Context

//必须将LifecycleProvider暴露给外部，子类继承自activity并不能继承module中的一些工厂方法，
// 不能直接找到LifecycleProvider
    fun lifecycleProvider():LifecycleProvider<*>
}