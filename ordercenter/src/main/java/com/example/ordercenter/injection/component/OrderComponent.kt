package com.example.ordercenter.injection.component

import com.example.baselibrary.component.ActivityComponent
import com.example.baselibrary.injection.PerComponentScope
import com.example.ordercenter.injection.module.OrderModule
import com.example.ordercenter.ui.activity.OrderComfirmActivity
import dagger.Component

/**
 * Created by user on 2018/3/15.
 */
@PerComponentScope
@Component(dependencies = arrayOf(ActivityComponent::class),
        modules = arrayOf(OrderModule::class))
interface OrderComponent {
    fun inject(activity: OrderComfirmActivity)
}