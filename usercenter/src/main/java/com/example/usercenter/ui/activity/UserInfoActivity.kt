package com.example.usercenter.ui.activity

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.util.Log
import com.bigkoo.alertview.AlertView
import com.bigkoo.alertview.OnItemClickListener
import com.example.baselibrary.common.BaseConstant
import com.example.baselibrary.data.protocol.UserInfo
import com.example.baselibrary.ext.onClick
import com.example.baselibrary.ui.activity.BaseMvpActivity
import com.example.baselibrary.utils.AppPrefsUtils
import com.example.baselibrary.utils.DateUtils
import com.example.baselibrary.utils.GlideUtils
import com.example.usercenter.R
import com.example.usercenter.injection.component.DaggerUserComponent
import com.example.usercenter.injection.module.UploadModule
import com.example.usercenter.presenter.UserInfoPresenter
import com.example.usercenter.view.UserInfoView
import com.jph.takephoto.app.TakePhoto
import com.jph.takephoto.app.TakePhotoImpl
import com.jph.takephoto.compress.CompressConfig
import com.jph.takephoto.model.TResult
import com.kotlin.provider.common.ProviderConstant
import com.qiniu.android.http.ResponseInfo
import com.qiniu.android.storage.UpCompletionHandler
import com.qiniu.android.storage.UploadManager
import kotlinx.android.synthetic.main.activity_user_info.*
import org.json.JSONObject
import pub.devrel.easypermissions.AfterPermissionGranted
import pub.devrel.easypermissions.EasyPermissions
import java.io.File

class UserInfoActivity : BaseMvpActivity<UserInfoPresenter>(),
        UserInfoView, TakePhoto.TakeResultListener {
    override fun onEditUserInfo(result: UserInfo) {

    }

    private lateinit var mTakePhoto: TakePhoto
    private lateinit var mTempFile: File
    private val muploadManager: UploadManager by lazy {
        UploadManager()
    }
    private var mLocalFile: String? = null
    private var mRemoteFile: String? = null
    private var mUserIcon: String? = null
    private var mUserName: String? = null
    private var mUserGender: String? = null
    private var mUserSign: String? = null
    private var mUserMobile: String? = null

    override fun injectComponent() {
        DaggerUserComponent.builder()
                .activityComponent(acitivtyComponent).uploadModule(UploadModule())
                .build().inject(this)
        mPresenter.mView = this
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_info)
        initView()
        initData()
        requestPermission()
    }

    private fun initData() {
        mUserIcon = AppPrefsUtils.getString(ProviderConstant.KEY_SP_USER_ICON)
        mUserName = AppPrefsUtils.getString(ProviderConstant.KEY_SP_USER_NAME)
        mUserGender = AppPrefsUtils.getString(ProviderConstant.KEY_SP_USER_GENDER)
        mUserSign = AppPrefsUtils.getString(ProviderConstant.KEY_SP_USER_SIGN)
        mUserMobile = AppPrefsUtils.getString(ProviderConstant.KEY_SP_USER_MOBILE)
        mRemoteFile = mUserIcon
        if (mUserIcon != "") {
            GlideUtils.loadUrlImage(this, mUserIcon!!, mUserIconIv)
        }
        mUserNameEt.setText(mUserName)
        if (mUserGender == "0") {
            mGenderMaleRb.isChecked = true
        } else {
            mGenderFemaleRb.isChecked = true
        }
        mUserSignEt.setText(mUserSign)
        mUserMobileTv.setText(mUserMobile)
    }

    private fun initView() {
        mUserIconIv.onClick {
            showAlertView()
        }
        mHeaderBar.getRightView().onClick {
            mPresenter.editUserInfo(mRemoteFile!!,
                    mUserNameEt.text?.toString() ?: "",
                    if (mGenderMaleRb.isChecked) "0" else "1",
                    mUserNameEt.text?.toString() ?: ""
            )
        }
    }

    @AfterPermissionGranted(101)
    private fun requestPermission() {
        if (Build.VERSION.SDK_INT >= 23) {
            //打电话的权限
            val mPermissionList = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA)
            if (EasyPermissions.hasPermissions(baseContext, mPermissionList[0], mPermissionList[1])) {
                //已经同意过
                mTakePhoto = TakePhotoImpl(this, this)
            } else {
                //未同意过,或者说是拒绝了，再次申请权限
                EasyPermissions.requestPermissions(
                        this@UserInfoActivity,  //上下文
                        "需要访问内存和照相机的权限", //提示文言
                        101, //请求码
                        mPermissionList[0], mPermissionList[1] //权限列表
                )
            }
        } else {
            //6.0以下，不需要授权
            mTakePhoto = TakePhotoImpl(this, this)
        }
    }

    private fun showAlertView() {
        AlertView("选择图片", "", "取消", null, arrayOf("拍照", "相册"), this,
                AlertView.Style.ActionSheet, object : OnItemClickListener {
            override fun onItemClick(o: Any?, position: Int) {
                mTakePhoto.onEnableCompress(CompressConfig.ofDefaultConfig(), false)
                when (position) {
                    0 -> {
                        createTempFile()
                        mTakePhoto.onPickFromCapture(Uri.fromFile(mTempFile))
                    }
                    1 -> mTakePhoto.onPickFromGallery()
                }
            }
        }).show()
    }

    override fun takeCancel() {

    }

    override fun takeFail(result: TResult?, msg: String?) {
        Log.d("UserInfoActivity", msg?.toString())
    }

    override fun takeSuccess(result: TResult?) {
        mLocalFile = result?.image?.compressPath
        mPresenter.getUploadToken()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        mTakePhoto.onActivityResult(requestCode, resultCode, data)
    }

    fun createTempFile() {
        val fileName = " ${DateUtils.curTime}.png"
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            this.mTempFile = File(Environment.getExternalStorageDirectory(), fileName)
            return
        }
        this.mTempFile = File(filesDir, fileName)
    }

    override fun onGetUploadTokenResult(result: String) {
        muploadManager.put(mLocalFile, null, result, object : UpCompletionHandler {
            override fun complete(key: String?, info: ResponseInfo?, response: JSONObject?) {
                mRemoteFile = BaseConstant.IMAGE_SERVER_ADDRESS + response?.get("hash")

                GlideUtils.loadImage(this@UserInfoActivity, mRemoteFile!!, mUserIconIv)
            }
        }, null)
    }
}