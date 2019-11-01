package com.android.luomeiji_driver.UI.password

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.service.autofill.UserData
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.android.luomeiji_driver.Base.LBaseAppCompatActivity
import com.android.luomeiji_driver.R
import com.android.luomeiji_driver.Tools.MyToast
import com.android.luomeiji_driver.UI.carowner.CarOwnerActivity
import com.android.luomeiji_driver.bean.SignUpBean
import com.android.luomeiji_driver.data.UserData_Java
import com.vondear.rxtool.RxKeyboardTool
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_setpsw.*

class SetPswActivity : LBaseAppCompatActivity<SetPswPersenter>(), ISetPswView {
    override fun singupsuccess(signUpBean: SignUpBean) {
        RxKeyboardTool.hideKeyboard(this, settingpsw_onepsw)
        RxKeyboardTool.hideKeyboard(this, settingpsw_twopsw)
        editor!!.putBoolean("islogin", true)
        editor!!.putString("username", phonenumber)
        editor!!.putString("password", settingpsw_onepsw.text.toString())
        editor!!.putString("driveruserid", signUpBean.data.driverUserId)
        editor!!.commit()
        UserData_Java.islogin = true
        UserData_Java.driveruserid = signUpBean.data.driverUserId
        when (type) {
            "1" -> {
                startActivity(Intent(this, CarOwnerActivity::class.java))
            }
            "2" -> {
                finish()
            }
        }
    }

    override fun signuperror(string: String) {
        MyToast.showMsg(string)
    }

    override fun initlayout(): Int {
        return R.layout.activity_setpsw
    }

    var phonenumber = ""
    var phonecode = ""

    var editor: SharedPreferences.Editor? = null
    var sp: SharedPreferences? = null
    var type = ""
    override fun initView() {

        sp = getSharedPreferences("USERINFO", Context.MODE_PRIVATE)
        editor = sp!!.edit()
        phonenumber = intent.getStringExtra("phonenumber")
        phonecode = intent.getStringExtra("phonecode")
        type = intent.getStringExtra("type")
        when (type) {
            "1" -> {
                settingpsw_ok_bt.text = "立 即 验 证"
                settingpsw_message.visibility = View.VISIBLE
            }
            "2" -> {
                settingpsw_ok_bt.text = "完 成"
                settingpsw_message.visibility = View.GONE
            }
        }
        settingpsw_not_layout.visibility = View.GONE
        settingpsw_ok_bt.setOnClickListener {
            psworpsw()
        }
        settingpsw_twopsw.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                if (settingpsw_twopsw.text.toString() != settingpsw_onepsw.text.toString()) {
                    settingpsw_not_layout.visibility = View.VISIBLE
//                    MyToast.showMsg("两次密码不一致")
                } else {
                    settingpsw_not_layout.visibility = View.GONE
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })
    }

    private fun psworpsw() {
        var onepsw = settingpsw_onepsw.text.toString()
        var twopsw = settingpsw_twopsw.text.toString()
        if (onepsw == "") {
            MyToast.showMsg("请输入密码")
            return
        }
        if (twopsw == "") {
            MyToast.showMsg("请确认密码")
            return
        }
        if (twopsw != onepsw) {
            settingpsw_not_layout.visibility = View.VISIBLE
            MyToast.showMsg("两次密码不一致")
            return
        }
        when (type) {
            "1" -> {
                mPersenter!!.singuppsw(phonenumber, settingpsw_onepsw.text.toString(), settingpsw_twopsw.text.toString(), phonecode)
            }
            "2" -> {
                mPersenter!!.findpsw(phonenumber, settingpsw_onepsw.text.toString(), phonecode)
            }
        }
    }

    override fun initPersenter() {
        mPersenter = SetPswPersenter(this, this)
    }

    override fun showlogin() {
    }

    override fun dissmisslogin() {
    }
}