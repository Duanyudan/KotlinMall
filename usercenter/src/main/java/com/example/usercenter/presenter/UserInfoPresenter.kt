package com.example.usercenter.presenter

import com.example.baselibrary.data.protocol.UserInfo
import com.example.baselibrary.ext.excute
import com.example.baselibrary.presenter.BasePresenter
import com.example.baselibrary.rx.BaseSubscriber
import com.example.usercenter.service.UploadService
import com.example.usercenter.service.UserService
import com.example.usercenter.view.UserInfoView
import javax.inject.Inject

/**
 * Created by Administrator on 2018/3/7.
 */
class UserInfoPresenter @Inject constructor() : BasePresenter<UserInfoView>() {
    @Inject
    lateinit var userService: UserService
    @Inject
    lateinit var uploadService: UploadService

    fun getUploadToken() {
        mView.showLoading()
        uploadService.getUploadToken()
                .excute(object : BaseSubscriber<String>(mView) {
                    override fun onNext(t: String) {
                        super.onNext(t)
                        mView.onGetUploadTokenResult(t)
                    }
                }, lifecycleProvider)
    }
    fun editUserInfo(userIcon: String, userName: String, userGender: String, userSign: String) {
        mView.showLoading()
        userService.editUser(userIcon,userName,userGender,userSign)
                .excute(object : BaseSubscriber<UserInfo>(mView) {
                    override fun onNext(t: UserInfo) {
                        mView.onEditUserInfo(t)
                    }
                }, lifecycleProvider)
    }
}
