package com.example.ordercenter.injection.module

import com.example.ordercenter.service.OrderService
import com.example.ordercenter.service.OrderServiceImpl
import com.example.ordercenter.service.ShipAddressService
import com.example.ordercenter.service.ShipAddressServiceImpl
import dagger.Module
import dagger.Provides

/**
 * Created by user on 2018/3/14.
 */
@Module
class ShipAddressModule {
    @Provides
    fun providesShipAddressService(service: ShipAddressServiceImpl): ShipAddressService {
        return service
    }

}