package com.example.injection.component

import com.example.baselibrary.component.ActivityComponent
import com.example.baselibrary.injection.PerComponentScope
import com.example.injection.module.CategoryModule
import com.example.ui.fragment.CategoryFragment
import dagger.Component

/**
 * Created by user on 2018/3/15.
 */
@PerComponentScope
@Component(dependencies = arrayOf(ActivityComponent::class),
        modules = arrayOf(CategoryModule::class))
interface CategoryComponent {
    fun inject(fragment: CategoryFragment)
}