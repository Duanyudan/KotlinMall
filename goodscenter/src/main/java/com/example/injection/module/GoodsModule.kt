package com.example.injection.module

import com.example.service.CategoryService
import com.example.service.CategoryServiceImpl
import com.example.service.GoodsService
import com.example.service.GoodsServiceImpl
import dagger.Module
import dagger.Provides

/**
 * Created by user on 2018/3/14.
 */
@Module
class GoodsModule {
    @Provides
    fun providesGoodsService(service: GoodsServiceImpl): GoodsService {
        return service
    }

}