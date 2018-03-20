package com.example.messagecenter

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import android.widget.ViewFlipper

/**
 * Created by user on 2018/3/20.
 */
class NewsFlipperView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {
    private val mFlipperView:ViewFlipper
    init {
        val rootView= View.inflate(context,R.layout.layout_news_flipper,null)
        mFlipperView = rootView.findViewById(R.id.mFlipperView)
        mFlipperView.setInAnimation(context,R.anim.news_bottom_in)
        mFlipperView.setOutAnimation(context,R.anim.news_bottom_out)

        addView(rootView)
    }

    private fun buildNewsView(text:String):View{
        val textView = TextView(context)
        textView.text=text
//        textView.textSize=px2sp
        textView.layoutParams = LayoutParams(LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT)
        return textView
    }
    fun setData(data:Array<String>){
        for (text in data){
            mFlipperView.addView(buildNewsView(text))
        }
        mFlipperView.startFlipping()
    }
}