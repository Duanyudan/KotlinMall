package com.example.baselibrary.component

import android.content.Context
import com.example.baselibrary.module.AppModule
import dagger.Component
import javax.inject.Singleton

/**
 * Created by user on 2018/3/15.
 */
@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {
    fun context(): Context
}