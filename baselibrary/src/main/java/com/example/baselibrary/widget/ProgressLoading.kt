package com.example.baselibrary.widget

import android.app.Dialog
import android.content.Context
import android.content.res.Resources
import android.graphics.drawable.AnimationDrawable
import android.view.Gravity
import android.widget.ImageView
import com.example.baselibrary.R
import org.jetbrains.anko.find

/**
 * Created by Administrator on 2018/3/15.
 */
class ProgressLoading private constructor(context: Context, themeResId: Int) :
        Dialog(context, themeResId) {

    companion object {
        private lateinit var mDialog: ProgressLoading
        private var animDrawable: AnimationDrawable? = null

        fun create(context: Context): ProgressLoading{
            mDialog = ProgressLoading(context, R.style.LightProgressDialog)
            mDialog.setContentView(R.layout.progress_dialog)
            mDialog.setCancelable(true)
            mDialog.setCanceledOnTouchOutside(false)
            mDialog.window.attributes.gravity = Gravity.CENTER

            val lp = mDialog.window.attributes
            lp.dimAmount = 0.2f //灰暗程度

            mDialog.window.attributes = lp

//            动画
            val loadingView = mDialog.find<ImageView>(R.id.iv_loading)
//            获取北京
            animDrawable = loadingView.background as AnimationDrawable

            return mDialog

        }
    }

    fun showLoading() {
        super.show()
        animDrawable?.start()
    }

    fun hideLoading() {
        super.dismiss()
        animDrawable?.stop()
    }
}