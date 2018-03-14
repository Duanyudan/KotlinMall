package com.example.usercenter.injection.module

import com.example.usercenter.service.UserService
import com.example.usercenter.service.impl.UserServiceImpl
import dagger.Module

/**
 * Created by user on 2018/3/14.
 */
@Module
class UserModule {
    fun providesUserService(service: UserServiceImpl): UserService {
        return service
    }
}