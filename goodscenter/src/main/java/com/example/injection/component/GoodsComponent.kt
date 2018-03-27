package com.example.injection.component

import com.example.baselibrary.component.ActivityComponent
import com.example.baselibrary.injection.PerComponentScope
import com.example.injection.module.CartModule
import com.example.injection.module.GoodsModule
import com.example.ui.activity.GoodsActivity
import com.example.ui.fragment.GoodsDetailTabOneFragment
import com.example.ui.fragment.GoodsDetailTabTwoFragment
import dagger.Component

/**
 * Created by user on 2018/3/15.
 */
@PerComponentScope
@Component(dependencies = arrayOf(ActivityComponent::class),
        modules = arrayOf(GoodsModule::class,CartModule::class))
interface GoodsComponent {
    fun inject(activity: GoodsActivity)
    fun inject(fragment: GoodsDetailTabOneFragment)
    fun inject(fragment: GoodsDetailTabTwoFragment)
}