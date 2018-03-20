package com.example.injection.module

import com.example.baselibrary.ui.fragment.BaseFragment
import com.example.service.CategoryService
import com.example.service.CategoryServiceImpl
import com.example.ui.fragment.CategoryFragment
import dagger.Module
import dagger.Provides

/**
 * Created by user on 2018/3/14.
 */
@Module
class CategoryModule {
    @Provides
    fun providesCategoryService(service: CategoryServiceImpl): CategoryService{
        return service
    }

}