package com.android.luomeiji_driver.UI.login

import android.content.Context
import android.util.Log
import com.android.luomeiji_driver.Base.LBasePersenter
import com.android.luomeiji_driver.Tools.GsonUtils
import com.android.luomeiji_driver.Tools.Utils
import com.android.luomeiji_driver.bean.LoginBean
import com.android.luomeiji_driver.network.ApiData
import com.android.luomeiji_driver.network.OnSuccessAndFaultListener
import com.android.luomeiji_driver.network.OnSuccessAndFaultSub

class LoginPersenter(mView: ILoginView, context: Context) :
    LBasePersenter<ILoginView>(mView, context) {
    fun logining(name: String, psw: String) {
        Log.d("登录：", name + "  " + psw)
        ApiData.login(name, psw, OnSuccessAndFaultSub(object : OnSuccessAndFaultListener {
            override fun onSuccess(result: String?) {
                Log.d("登录", result)
                if (Utils.strinjson(result, "code") == "1") {
                    var loginbean = GsonUtils.fromJson(result, LoginBean::class.java)
                    mView.loginsuccess(loginbean)
                } else {
                    mView.loginerror(Utils.strinjson(result, "message"))
                }
            }

            override fun onFault(errorMsg: String?) {
                mView.loginerror(errorMsg!!)
            }
        }))
    }
}