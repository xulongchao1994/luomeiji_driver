package com.android.luomeiji_driver.UI.welcome

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.service.autofill.UserData
import com.android.luomeiji_driver.Base.LBaseAppCompatActivity
import com.android.luomeiji_driver.R
import com.android.luomeiji_driver.UI.carowner.CarOwnerActivity
import com.android.luomeiji_driver.UI.login.LoginActivity
import com.android.luomeiji_driver.UI.main.MainActivity
import com.android.luomeiji_driver.data.UserData_Java

class WelcomeActivity : LBaseAppCompatActivity<WelcomePersenter>(), IWelcomeView {
    override fun getlegalizesuccess(string: String) {
        UserData_Java.driverReviewState = string
        if (UserData_Java.driverReviewState == "2" || UserData_Java.driverReviewState == "3") {
            startActivity(Intent(this, CarOwnerActivity::class.java))
            finish()
        } else if (UserData_Java.driverReviewState == "1") {
            startActivity(Intent(this, MainActivity::class.java))
        } else if (UserData_Java.driverReviewState == "0") {//正在审核
        }
        finish()
    }

    override fun getlegalizeerror(string: String) {
    }

    override fun initlayout(): Int {
        return R.layout.activity_welcome
    }

    private var sp: SharedPreferences? = null
    var isfirst = false
    override fun initView() {
        sp = getSharedPreferences("USERINFO", Context.MODE_PRIVATE)
        UserData_Java.islogin = sp!!.getBoolean("islogin", false)
        isfirst = sp!!.getBoolean("isfirst", false)
        if (UserData_Java.islogin) {
            UserData_Java.driveruserid = sp!!.getString("driveruserid", "").toString()
            UserData_Java.driverReviewState = sp!!.getString("driverReviewState", "").toString()
            mPersenter!!.getlegalize(UserData_Java.driveruserid)
        } else {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }

    override fun initPersenter() {
        mPersenter = WelcomePersenter(this, this)
    }

    override fun showlogin() {
    }

    override fun dissmisslogin() {
    }
}