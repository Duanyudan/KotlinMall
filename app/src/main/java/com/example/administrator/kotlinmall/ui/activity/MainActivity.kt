package com.example.administrator.kotlinmall.ui.activity

import android.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.administrator.kotlinmall.R
import com.example.administrator.kotlinmall.ui.fragment.HomeFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    private fun initView() {

        val manager = supportFragmentManager.beginTransaction()
        manager.replace(R.id.mContaier,HomeFragment())
        manager.commit()
    }
}
