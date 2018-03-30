package com.example.paysdk.injection.component

import com.example.baselibrary.component.ActivityComponent
import com.example.baselibrary.injection.PerComponentScope
import com.example.paysdk.injection.module.PayModule
import com.example.paysdk.ui.CashRegisterActivity
import dagger.Component

/**
 * Created by user on 2018/3/15.
 */
@PerComponentScope
@Component(dependencies = arrayOf(ActivityComponent::class),
        modules = arrayOf(PayModule::class))
interface PayComponent {
    fun inject(activity: CashRegisterActivity)
}