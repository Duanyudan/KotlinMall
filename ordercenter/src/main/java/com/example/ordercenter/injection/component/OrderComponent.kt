package com.example.ordercenter.injection.component

import com.example.baselibrary.component.ActivityComponent
import com.example.baselibrary.injection.PerComponentScope
import com.example.ordercenter.injection.module.OrderModule
import com.example.ordercenter.ui.activity.OrderComfirmActivity
import com.example.ordercenter.ui.activity.OrderDetailActivity
import com.example.ordercenter.ui.fragment.OrderFragment
import dagger.Component

/**
 * Created by user on 2018/3/15.
 */
@PerComponentScope
@Component(dependencies = arrayOf(ActivityComponent::class),
        modules = arrayOf(OrderModule::class))
interface OrderComponent {
    fun inject(activity: OrderComfirmActivity)
    fun inject(fragment: OrderFragment)
    fun inject(activity: OrderDetailActivity)
}