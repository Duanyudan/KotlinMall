package com.example.baselibrary.rx

import rx.Subscriber

/**
 * Created by Administrator on 2018/3/7.
 */
open class BaseSubscriber<T> : Subscriber<T>() {
    override fun onCompleted() {
    }

    override fun onError(e: Throwable?) {
    }

    override fun onNext(t: T) {
    }
}