package com.android.luomeiji_driver.UI.withdraw

import com.android.luomeiji_driver.Base.LBaseAppCompatActivity
import com.android.luomeiji_driver.R

class WithdrawActivity : LBaseAppCompatActivity<WithdrawPersenter>(), IWithdrawView {
    override fun initlayout(): Int {
        return R.layout.activity_withdraw
    }

    override fun initView() {
    }

    override fun initPersenter() {
        mPersenter = WithdrawPersenter(this, this)
    }

    override fun showlogin() {
        mLoading!!.show()
    }

    override fun dissmisslogin() {
        mLoading!!.dismiss()
    }
}