package com.example.baselibrary.ext

import com.example.baselibrary.rx.BaseSubscriber
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by Administrator on 2018/3/7.
 */
fun <T> Observable<T>.excute(subscriber: BaseSubscriber<T>) {
    this.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(subscriber)
}