package com.example.injection.module

import com.example.service.CartService
import com.example.service.CartServiceImpl
import dagger.Module
import dagger.Provides

/**
 * Created by user on 2018/3/14.
 */
@Module
class CartModule {
    @Provides
    fun providesCartService(service: CartServiceImpl): CartService {
        return service
    }

}