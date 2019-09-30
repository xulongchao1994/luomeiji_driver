package com.android.luomeiji_driver.UI.corps

import com.android.luomeiji_driver.Base.LBaseAppCompatActivity
import com.android.luomeiji_driver.R

class SelectcorpsActivity : LBaseAppCompatActivity<SelectcorpsPersenter>(), ISelectcorpsView {
    override fun initlayout(): Int {
        return R.layout.activity_selectcorps
    }

    override fun initView() {
    }

    override fun initPersenter() {
        mPersenter = SelectcorpsPersenter(this, this)
    }

    override fun showlogin() {
    }

    override fun dissmisslogin() {
    }
}