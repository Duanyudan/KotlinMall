package com.example.baselibrary.module

import android.content.Context
import com.example.baselibrary.common.BaseApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by user on 2018/3/15.
 */
@Module
class AppModule(private val context: BaseApplication) {
    @Provides
    @Singleton
    fun providesContext(): Context {
        return context
    }
}