package com.android.luomeiji_driver.UI.signup

import android.content.Context
import android.util.Log
import com.android.luomeiji_driver.Base.LBasePersenter
import com.android.luomeiji_driver.Tools.GsonUtils
import com.android.luomeiji_driver.Tools.Utils
import com.android.luomeiji_driver.bean.LoginBean
import com.android.luomeiji_driver.network.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.Observer

class SignUpPersenter(mView: ISignUpView, context: Context) :
    LBasePersenter<ISignUpView>(mView, context) {
    fun getcode(phonenumber: String) {
        ApiData.getcode(phonenumber, OnSuccessAndFaultSub(object : OnSuccessAndFaultListener {
            override fun onSuccess(result: String?) {
                if (Utils.strinjson(result, "code") == "1") {
                    var data = Utils.strinjson(result, "data")
                    mView.getcodesuccess(data)
                } else {
                    mView.getcodeerror(Utils.strinjson(result, "message"))
                }
            }

            override fun onFault(errorMsg: String?) {
                mView.getcodeerror(errorMsg!!)
            }
        }))

//        mApi!!.getcode2(phonenumber)
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribeOn(Schedulers.io())
//            .subscribe(object : Observer<GetCodeBean> {
//                override fun onComplete() {
//                }
//
//                override fun onSubscribe(d: Disposable) {
//                    mDisposable = d
//                }
//
//                override fun onNext(t: GetCodeBean) {
//                }
//
//                override fun onError(e: Throwable) {
//                }
//            })
    }

    fun getfindpswcode(phonenumber: String) {
        ApiData.getfindpswcode(phonenumber, OnSuccessAndFaultSub(object : OnSuccessAndFaultListener {
            override fun onSuccess(result: String?) {
                mView.getcodesuccess(result!!)
            }

            override fun onFault(errorMsg: String?) {
                mView.getcodeerror(errorMsg!!)
            }
        }))
    }
}