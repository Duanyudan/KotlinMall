package com.example.paysdk.injection.module

import com.example.paysdk.service.PayService
import com.example.paysdk.service.PayServiceImpl
import dagger.Module
import dagger.Provides

/**
 * Created by user on 2018/3/14.
 */
@Module
class PayModule {
    @Provides
    fun providesPayService(service: PayServiceImpl):PayService {
        return service
    }

}