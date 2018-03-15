package com.example.baselibrary.module

import android.app.Activity
import com.trello.rxlifecycle.LifecycleProvider
import dagger.Module
import dagger.Provides

/**
 * Created by user on 2018/3/15.
 */
@Module
class LifecycleProvidersModule(private val lifecycleProvider: LifecycleProvider<*>) {
    @Provides
    fun provideslifecycleProvider(): LifecycleProvider<*> {
        return lifecycleProvider
    }
}