package com.android.luomeiji_driver.UI.bindbank

import android.text.Layout
import androidx.core.content.ContextCompat
import com.android.luomeiji_driver.Base.LBaseAppCompatActivity
import com.android.luomeiji_driver.R
import com.vondear.rxtool.RxTextTool
import kotlinx.android.synthetic.main.activity_dontbindbank.*

class DontBindBankActivity : LBaseAppCompatActivity<BindBankPersenter>(), IBindBankView {
    override fun initlayout(): Int {
        return R.layout.activity_dontbindbank
    }

    override fun initView() {
        RxTextTool.getBuilder("").setBold().setAlign(Layout.Alignment.ALIGN_CENTER)
            .append("为了您的账户资金安全，罗美集司机端").append("只支持绑定一张银行卡")
            .setForegroundColor(ContextCompat.getColor(this, R.color.dontbindbank_text))
            .append(",确保资金只能提现到您的银行卡中，他人无法盗用")
            .into(bindbank_text)
    }

    override fun initPersenter() {
        mPersenter = BindBankPersenter(this, this)
    }

    override fun showlogin() {
    }

    override fun dissmisslogin() {
    }
}