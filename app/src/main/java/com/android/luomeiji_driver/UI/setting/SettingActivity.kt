package com.android.luomeiji_driver.UI.setting

import com.android.luomeiji_driver.Base.LBaseAppCompatActivity
import com.android.luomeiji_driver.R
import kotlinx.android.synthetic.main.activity_setting.*

class SettingActivity : LBaseAppCompatActivity<SettingPersenter>(), ISettingView {
    override fun initlayout(): Int {
        return R.layout.activity_setting
    }

    override fun initView() {
        setting_aboutus.setOnClickListener { }
        setting_outlogin.setOnClickListener { }
        setting_psw.setOnClickListener { }
        setting_back.setOnClickListener { finish() }
    }

    override fun initPersenter() {
        mPersenter = SettingPersenter(this, this)
    }

    override fun showlogin() {
    }

    override fun dissmisslogin() {
    }
}