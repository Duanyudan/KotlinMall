package com.example.injection.component

import com.example.baselibrary.component.ActivityComponent
import com.example.baselibrary.injection.PerComponentScope
import com.example.injection.module.CartModule
import com.example.ui.fragment.CartFragment
import dagger.Component

/**
 * Created by user on 2018/3/15.
 */
@PerComponentScope
@Component(dependencies = arrayOf(ActivityComponent::class),
        modules = arrayOf(CartModule::class))
interface CartComponent {
fun inject(fragment:CartFragment)
}