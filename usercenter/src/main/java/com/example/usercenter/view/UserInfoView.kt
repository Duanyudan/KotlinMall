package com.example.usercenter.view

import com.example.baselibrary.data.protocol.UserInfo
import com.example.baselibrary.presenter.view.BaseView

/**
 * Created by Administrator on 2018/3/7.
 */
interface UserInfoView : BaseView {

    fun onGetUploadTokenResult(result:String)

    fun onEditUserInfo(result:UserInfo)
}