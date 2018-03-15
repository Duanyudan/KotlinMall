package com.example.baselibrary.injection

import java.lang.annotation.Documented
import java.lang.annotation.Retention
import javax.inject.Scope
import java.lang.annotation.RetentionPolicy.RUNTIME

/**
 * Created by user on 2018/3/15.
 */
@Scope
@Documented
@Retention(RUNTIME)
annotation class ActivityScope