package com.example.baselibrary.ext

import android.graphics.drawable.AnimationDrawable
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import com.example.baselibrary.R
import com.example.baselibrary.data.protocol.BaseResp
import com.example.baselibrary.rx.BaseFunc
import com.example.baselibrary.rx.BaseFuncBoolean
import com.example.baselibrary.rx.BaseSubscriber
import com.example.baselibrary.utils.GlideUtils
import com.example.baselibrary.widgets.DefaultTextWatcher
import com.kennyc.view.MultiStateView
import com.trello.rxlifecycle.LifecycleProvider
import org.jetbrains.anko.find
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by Administrator on 2018/3/7.
 */
fun <T> Observable<T>.excute(subscriber: BaseSubscriber<T>,
                             lifecycleProvider: LifecycleProvider<*>) {
    this.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .compose(lifecycleProvider.bindToLifecycle())
            .subscribe(subscriber)
}

fun <T> Observable<BaseResp<T>>.convert(): Observable<T> {
    return this.flatMap(BaseFunc())
}

fun <T> Observable<BaseResp<T>>.convertBoolean(): Observable<Boolean> {
    return this.flatMap(BaseFuncBoolean())
}

fun View.onClick(listener: View.OnClickListener) {
    this.setOnClickListener(listener)
}

fun View.onClick(method: () -> Unit) {
    this.setOnClickListener { method() }


}

fun Button.enable(et: EditText, method: () -> Boolean) {
    val btn = this
    et.addTextChangedListener(object : DefaultTextWatcher() {
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            btn.isEnabled = method()
        }
    })
}

fun ImageView.loadUrl(url: String) {
    GlideUtils.loadUrlImage(context, url, this)
}

fun MultiStateView.startLoading() {
    viewState = MultiStateView.VIEW_STATE_LOADING
    val loading = getView(MultiStateView.VIEW_STATE_LOADING)
    val animBg = loading!!.find<View>(R.id.loading_anim_view)
    (animBg as AnimationDrawable).start()
}

fun View.setVisible(boolean: Boolean) {
    if (boolean) {
        this.visibility = View.VISIBLE
    } else {
        this.visibility = View.GONE
    }
}