package com.android.luomeiji_driver.UI.password

import android.content.Context
import android.util.Log
import com.android.luomeiji_driver.Base.LBasePersenter
import com.android.luomeiji_driver.Tools.GsonUtils
import com.android.luomeiji_driver.bean.SignUpBean
import com.android.luomeiji_driver.network.ApiData
import com.android.luomeiji_driver.network.GetCodeBean
import com.android.luomeiji_driver.network.OnSuccessAndFaultListener
import com.android.luomeiji_driver.network.OnSuccessAndFaultSub
import com.google.gson.Gson
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import org.json.JSONObject

class SetPswPersenter(mView: ISetPswView, context: Context) :
    LBasePersenter<ISetPswView>(mView, context) {

    fun singuppsw(phoneNum: String, password: String, verPassword: String, code: String) {
        Log.d("设置密码：", phoneNum + "  " + password + "  " + verPassword + "  " + code)
        ApiData.setpsw(phoneNum, password, verPassword, code, OnSuccessAndFaultSub(object : OnSuccessAndFaultListener {
            override fun onSuccess(result: String?) {
                var json = JSONObject(result)
                var mseeage = json.get("message").toString()
                var code = json.get("code").toString()
                if (code == "1") {
                    var signUpBean = GsonUtils.fromJson(result, SignUpBean::class.java)
                    mView.singupsuccess(signUpBean)
                } else {
                    mView.signuperror(mseeage)
                }
            }

            override fun onFault(errorMsg: String?) {
                mView.signuperror(errorMsg!!)
            }
        }))

//        mApi!!.signup(phoneNum, password, verPassword, code)
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribeOn(Schedulers.io())
//            .subscribe(object : Observer<SignUpBean> {
//                override fun onNext(t: SignUpBean) {
//                    Log.d("设置密码", t!!.toString())
//                    if (t!!.code == "1") {
//                        mView.singupsuccess(t)
//                    } else
//                        mView.signuperror(t.msg)
//                }
//
//                override fun onComplete() {
//                }
//
//                override fun onSubscribe(d: Disposable) {
//                    mDisposable = d
//                }
//
//
//                override fun onError(e: Throwable) {
//                    Log.d("设置密码", e!!.toString())
//                    mView.signuperror(e!!.message.toString())
//                }
//            })
    }

    fun findpsw(phoneNum: String, password: String, code: String) {
        Log.d("设置密码：", phoneNum + "  " + password + "  " + code)
        ApiData.findpsw(phoneNum, password, code, OnSuccessAndFaultSub(object : OnSuccessAndFaultListener {
            override fun onSuccess(result: String?) {
                var json = JSONObject(result)
                var mseeage = json.get("message").toString()
                var code = json.get("code").toString()
                if (code == "1") {
                    var signUpBean = GsonUtils.fromJson(result, SignUpBean::class.java)
                    mView.singupsuccess(signUpBean)
                } else {
                    mView.signuperror(mseeage)
                }
            }

            override fun onFault(errorMsg: String?) {
                mView.signuperror(errorMsg!!)
            }
        }))

    }


}