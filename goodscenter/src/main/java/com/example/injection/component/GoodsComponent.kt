package com.example.injection.component

import com.example.baselibrary.component.ActivityComponent
import com.example.baselibrary.injection.PerComponentScope
import com.example.injection.module.GoodsModule
import com.example.ui.activity.GoodsActivity
import dagger.Component

/**
 * Created by user on 2018/3/15.
 */
@PerComponentScope
@Component(dependencies = arrayOf(ActivityComponent::class),
        modules = arrayOf(GoodsModule::class))
interface GoodsComponent {
    fun inject(activity: GoodsActivity)
}