package com.android.luomeiji_driver.UI.mend

import com.android.luomeiji_driver.Base.LBaseAppCompatActivity
import com.android.luomeiji_driver.R

class MendActivity : LBaseAppCompatActivity<MendPersenter>(), IMendView {
    override fun initlayout(): Int {
        return R.layout.activity_mend
    }

    override fun initView() {
    }

    override fun initPersenter() {
        mPersenter = MendPersenter(this, this)
    }

    override fun showlogin() {
    }

    override fun dissmisslogin() {
    }
}