package com.example.ordercenter.injection.component

import com.example.baselibrary.component.ActivityComponent
import com.example.baselibrary.injection.PerComponentScope
import com.example.ordercenter.injection.module.ShipAddressModule
import com.example.ordercenter.ui.activity.ShipAddressActivity
import com.example.ordercenter.ui.activity.ShipAddressEditActivity
import dagger.Component

/**
 * Created by user on 2018/3/15.
 */
@PerComponentScope
@Component(dependencies = arrayOf(ActivityComponent::class),
        modules = arrayOf(ShipAddressModule::class))
interface ShipAddressComponent {
    fun inject(activity: ShipAddressActivity)
    fun inject(activity: ShipAddressEditActivity)
}