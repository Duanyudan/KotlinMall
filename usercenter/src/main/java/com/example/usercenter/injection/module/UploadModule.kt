package com.example.usercenter.injection.module

import com.example.usercenter.service.UploadService
import com.example.usercenter.service.UserService
import com.example.usercenter.service.impl.UploadServiceImpl
import com.example.usercenter.service.impl.UserServiceImpl
import dagger.Module
import dagger.Provides

/**
 * Created by user on 2018/3/14.
 */
@Module
class UploadModule {
    @Provides
    fun providesUploadService(service: UploadServiceImpl): UploadService {
        return service
    }
}