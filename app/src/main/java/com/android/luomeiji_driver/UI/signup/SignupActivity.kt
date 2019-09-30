package com.android.luomeiji_driver.UI.signup

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Message
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.android.luomeiji_driver.Base.LBaseAppCompatActivity
import com.android.luomeiji_driver.R
import com.android.luomeiji_driver.Tools.MyToast
import com.android.luomeiji_driver.UI.password.SetPswActivity
import com.vondear.rxtool.RxActivityTool
import kotlinx.android.synthetic.main.activity_sigeup.*

class SignupActivity : LBaseAppCompatActivity<SignUpPersenter>(), ISignUpView,
    View.OnClickListener {
    var code_m = ""
    override fun getcodesuccess(code: String) {
        code_m = code
    }

    override fun getcodeerror(error: String) {
        MyToast.showMsg(error)
    }

    override fun onClick(p0: View?) {
        when (p0!!.id) {
            R.id.sigeup_code -> {
                sigeup_code.isEnabled = false
                starttime(60)
                mPersenter!!.getcode(sigeup_phonenumber_et.text.toString())
            }
            R.id.sigeup_sigeup_bt -> {
                if (login_code_et.text.toString() == "") {
                    MyToast.showMsg("请输入验证码")
                    return
                }
                if (login_code_et.text.toString() != code_m) {
                    MyToast.showMsg("验证码错误")
                    return
                }
                var intent = Intent(this, SetPswActivity::class.java)
                intent.putExtra("phonenumber", sigeup_phonenumber_et.text.toString())
                intent.putExtra("phonecode", login_code_et.text.toString())
                intent.putExtra("type", type == "1")
                startActivity(intent)
            }
        }
    }

    // 倒计时60秒
    var tiem_c: CountDownTimer? = null
    var hand = @SuppressLint("HandlerLeak")
    object : Handler() {
        override fun handleMessage(msg: Message?) {
            when (msg.toString()) {
                "1" -> {
                    tiem_c!!.cancel()
                    sigeup_code.text = "获取验证码"
                    sigeup_code.isEnabled = true
                }
            }
        }
    }

    fun starttime(time: Long) {
        tiem_c = object : CountDownTimer(time * 1000, 1000) {
            override fun onFinish() {
                val msg = Message.obtain()
                msg.obj = android.R.attr.data
                msg.what = 1   //标志消息的标志
                hand.sendMessage(msg)
            }

            override fun onTick(millisUntilFinished: Long) {
                Log.d("orderinfo", millisUntilFinished.toString())
                var tiemint = millisUntilFinished / 1000
                sigeup_code.text = "$tiemint 秒"
                if (millisUntilFinished <= 1000) {
                    tiem_c!!.cancel()
                    sigeup_code.text = "获取验证码"
                    sigeup_code.isEnabled = true
                    val msg = Message.obtain()
                    msg.obj = android.R.attr.data
                    msg.what = 1   //标志消息的标志
                    hand.sendMessage(msg)
                }
            }
        }
        tiem_c!!.start()
    }

    override fun initlayout(): Int {
        return R.layout.activity_sigeup
    }

    var type = ""
    override fun initView() {
        type = intent.getStringExtra("type")
        when (type) {
            "1" -> {
                sigeup_sigeup_bt.text = "下一步"
                sigeup_forgetpsw.visibility = View.INVISIBLE
            }
            "2" -> {
                sigeup_sigeup_bt.text = "注册"
                sigeup_forgetpsw.visibility = View.VISIBLE
            }
        }
        RxActivityTool.addActivity(this)
        sigeup_code.setOnClickListener(this)
        sigeup_sigeup_bt.setOnClickListener(this)
        sigeup_forgetpsw.setOnClickListener {
            type = "2"
            sigeup_sigeup_bt.text = "下一步"
            sigeup_forgetpsw.visibility = View.INVISIBLE
        }
    }

    override fun initPersenter() {
        mPersenter = SignUpPersenter(this, this)
    }

    override fun showlogin() {
        mLoading!!.show()
    }

    override fun dissmisslogin() {
        mLoading!!.dismiss()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (tiem_c != null) {
            tiem_c!!.onFinish()
            tiem_c!!.cancel()
            tiem_c == null
        }
        sigeup_code.text = "获取验证码"
        sigeup_code.isEnabled = true
    }

    override fun onResume() {
        super.onResume()
        if (tiem_c != null) {
            tiem_c!!.onFinish()
            tiem_c!!.cancel()
            tiem_c == null
        }
//        sigeup_code.text = "获取验证码"
//        sigeup_code.isEnabled = true
    }
}