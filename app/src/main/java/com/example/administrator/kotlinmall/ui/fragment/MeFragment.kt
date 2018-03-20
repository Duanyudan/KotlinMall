package com.example.administrator.kotlinmall.ui.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.administrator.kotlinmall.R
import com.example.administrator.kotlinmall.ui.activity.SettingActivity
import com.example.administrator.kotlinmall.ui.adapter.HomeDiscountAdapter
import com.example.baselibrary.common.BaseConstant
import com.example.baselibrary.ext.loadUrl
import com.example.baselibrary.ext.onClick
import com.example.baselibrary.ui.fragment.BaseFragment
import com.example.baselibrary.utils.AppPrefsUtils
import com.example.baselibrary.widgets.BannerImageLoader
import com.example.provider.common.ProviderConstant
import com.example.provider.common.isLogined
import com.example.usercenter.ui.activity.UserInfoActivity
import com.kotlin.mall.common.*
import com.kotlin.mall.ui.adapter.TopicAdapter
import com.youth.banner.BannerConfig
import com.youth.banner.Transformer
import kotlinx.android.synthetic.main.fragment_me.*
import me.crosswall.lib.coverflow.CoverFlow
import org.jetbrains.anko.support.v4.startActivity

/**
 * Created by user on 2018/3/19.
 */
class MeFragment : BaseFragment(), View.OnClickListener {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_me, null)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {

        mUserIconIv.onClick(this)
        mSettingTv.onClick(this)
        mUserNameTv.onClick(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.mUserIconIv, R.id.mUserNameTv -> {
                if (isLogined()) {
                    startActivity<UserInfoActivity>()
                }
            }
            R.id.mSettingTv -> {
                startActivity<SettingActivity>()
            }
        }
    }


    override fun onStart() {
        super.onStart()
        loadData()
    }

    private fun loadData() {
        if (!isLogined()) {
            mUserIconIv.setImageResource(R.drawable.icon_default_user)
            mUserNameTv.text = getString(R.string.un_login_text)
        } else {
            val userIcon = AppPrefsUtils.getString(ProviderConstant.KEY_SP_USER_ICON)
            if (userIcon.isNotEmpty()) {
                mUserIconIv.loadUrl(userIcon)
            }
            mUserNameTv.text = AppPrefsUtils.getString(ProviderConstant.KEY_SP_USER_NAME)
        }
    }
}