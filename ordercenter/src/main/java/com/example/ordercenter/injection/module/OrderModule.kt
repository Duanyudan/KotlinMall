package com.example.ordercenter.injection.module

import com.example.ordercenter.service.OrderService
import com.example.ordercenter.service.OrderServiceImpl
import dagger.Module
import dagger.Provides

/**
 * Created by user on 2018/3/14.
 */
@Module
class OrderModule {
    @Provides
    fun providesOrderService(service: OrderServiceImpl): OrderService {
        return service
    }

}