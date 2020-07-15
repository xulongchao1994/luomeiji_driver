package com.android.luomeiji_driver.UI.login

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.view.View
import com.android.luomeiji_driver.Base.LBaseAppCompatActivity
import com.android.luomeiji_driver.R
import com.android.luomeiji_driver.Tools.MyToast
import com.android.luomeiji_driver.UI.carowner.CarOwnerActivity
import com.android.luomeiji_driver.UI.main.MainActivity
import com.android.luomeiji_driver.UI.signup.SignupActivity
import com.android.luomeiji_driver.bean.LoginBean
import com.android.luomeiji_driver.data.UserData_Java
import com.vondear.rxtool.RxKeyboardTool
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : LBaseAppCompatActivity<LoginPersenter>(), ILoginView {
    override fun loginsuccess(string: LoginBean) {
        RxKeyboardTool.hideKeyboard(this, login_name_et )
        RxKeyboardTool.hideKeyboard(this, login_psw_et)
        editor!!.putBoolean("islogin", true)
        editor!!.putString("username", login_name_et.text.toString())
        editor!!.putString("password", login_psw_et.text.toString())
        editor!!.putString("driveruserid", string.data.driverUserId)
        editor!!.putString("driverReviewState", string.data.driverReviewState)
        editor!!.commit()
        UserData_Java.islogin = true
        UserData_Java.driveruserid = string.data.driverUserId
        when (string.data.driverReviewState) {
            "0" -> {
                MyToast.showMsg("司机认证正在审核中")
            }
            "1" -> {
                startActivity(Intent(this, MainActivity::class.java))
            }
            "2" -> {
                MyToast.showMsg("认证未通过")
                startActivity(Intent(this, CarOwnerActivity::class.java))
            }
            "3" -> {
                startActivity(Intent(this, CarOwnerActivity::class.java))
            }
        }
    }

    override fun loginerror(string: String) {
    }

    override fun initlayout(): Int {
        return R.layout.activity_login
    }

    var editor: SharedPreferences.Editor? = null
    var sp: SharedPreferences? = null
    override fun initView() {

        sp = getSharedPreferences("USERINFO", Context.MODE_PRIVATE)
        editor = sp!!.edit()
        login_singin.setOnClickListener {
            startActivity(Intent(this, SignupActivity::class.java).putExtra("type", "1"))
        }
        login_forgetpsw.setOnClickListener {
            startActivity(Intent(this, SignupActivity::class.java).putExtra("type", "2"))
        }
        login_login_bt.setOnClickListener {
            var name = login_name_et.text.toString()
            var psw = login_psw_et.text.toString()
            if (name == "") {
                MyToast.showMsg("请输入手机号")
                return@setOnClickListener
            }
            if (psw == "") {
                MyToast.showMsg("请输入密码")
                return@setOnClickListener
            }
            mPersenter!!.logining(name, psw)
        }
    }

    override fun initPersenter() {
        mPersenter = LoginPersenter(this, this)
    }

    override fun showlogin() {
    }

    override fun dissmisslogin() {
    }

}