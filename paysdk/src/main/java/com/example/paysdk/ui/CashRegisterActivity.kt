package com.example.paysdk.ui

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alipay.sdk.app.EnvUtils
import com.alipay.sdk.app.PayTask
import com.example.baselibrary.ext.onClick
import com.example.baselibrary.ui.activity.BaseMvpActivity
import com.example.baselibrary.utils.YuanFenConverter
import com.example.paysdk.R
import com.example.paysdk.injection.component.DaggerPayComponent
import com.example.paysdk.injection.module.PayModule
import com.example.paysdk.presenter.PayPresenter
import com.example.paysdk.presenter.view.PayView
import com.example.provider.common.ProviderConstant
import com.example.provider.router.RouterPath
import kotlinx.android.synthetic.main.activity_cash_register.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread

/**
 * Created by user on 2018/3/30.
 */
@Route(path = RouterPath.PaySDK.PATH_PAY)
class CashRegisterActivity : BaseMvpActivity<PayPresenter>(), PayView {

    var mOrderId: Int = 0

    var mTotalPrice: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cash_register)

        EnvUtils.setEnv(EnvUtils.EnvEnum.SANDBOX)
        initView()
        initData()
    }

    private fun initData() {
        mOrderId = intent.getIntExtra(ProviderConstant.KEY_ORDER_ID, -1)
        mTotalPrice = intent.getLongExtra(ProviderConstant.KEY_ORDER_PRICE, -1)
        mTotalPriceTv.text = YuanFenConverter.changeF2YWithUnit(mTotalPrice)
    }

    private fun initView() {
        mPayBtn.onClick {
            mPresenter.getPaySign(mOrderId, mTotalPrice)
        }
    }

    override fun onGetPaySignResult(result: String) {
        doAsync {
            val resultMap: Map<String, String> = PayTask(this@CashRegisterActivity)
                    .payV2(result, true)
            uiThread {
                if (resultMap["resultStatus"].equals("9000")) {
                    mPresenter.payOrder(mOrderId)
                } else {
                    toast("支付失败${resultMap["memo"]}")
                }
            }
        }
    }

    override fun onPayOrderResult(result: String) {
        finish()
    }

    override fun injectComponent() {
        DaggerPayComponent.builder().activityComponent(acitivtyComponent)
                .payModule(PayModule())
                .build().inject(this)
        mPresenter.mView = this
    }

}