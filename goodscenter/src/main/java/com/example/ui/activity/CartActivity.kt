package com.example.ui.activity

import android.os.Bundle
import com.example.baselibrary.ui.activity.BaseActivity
import com.example.goodscenter.R
import com.example.ui.fragment.CartFragment

/**
 * Created by Administrator on 2018/3/27.
 */
class CartActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

       val fragment= supportFragmentManager.findFragmentById(R.id.fragment_cart)
        (fragment as CartFragment).setBackVisible(true)
    }
}