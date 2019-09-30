package com.android.luomeiji_driver.UI.bindbank

import com.android.luomeiji_driver.Base.LBaseAppCompatActivity
import com.android.luomeiji_driver.R

class BindBankActivity : LBaseAppCompatActivity<BindBankPersenter>(), IBindBankView {
    override fun initlayout(): Int {
        return R.layout.activity_bindbank
    }

    override fun initView() {
    }

    override fun initPersenter() {
        mPersenter = BindBankPersenter(this, this)
    }

    override fun showlogin() {
    }

    override fun dissmisslogin() {
    }
}