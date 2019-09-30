package com.android.luomeiji_driver.UI.carowner

import android.content.Context
import android.database.Observable
import android.util.Log
import com.android.luomeiji_driver.Base.LBasePersenter
import com.android.luomeiji_driver.Tools.GsonUtils
import com.android.luomeiji_driver.Tools.Utils
import com.android.luomeiji_driver.network.ApiData
import com.android.luomeiji_driver.network.GetCodeBean
import com.android.luomeiji_driver.network.OnSuccessAndFaultListener
import com.android.luomeiji_driver.network.OnSuccessAndFaultSub
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.*

class CarOwnerPersenter(mView: ICarOwnerView, context: Context) :
    LBasePersenter<ICarOwnerView>(mView, context) {
    fun owner(carid: String, idname: String, idnumber: String) {
        Log.d("认证第一步:", carid + "  " + idname + "  " + idnumber)
//        mApi!!.ownerone(carid, idname, idnumber)
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribeOn(Schedulers.io())
//            .subscribe(object : Observer<GetCodeBean> {
//                override fun onComplete() {
//
//                }
//
//                override fun onSubscribe(d: Disposable) {
//                    mDisposable = d
//                }
//
//                override fun onNext(t: GetCodeBean) {
//                    if (t!!.code == "1") {
//                        mView.carownersuccess(t)
//                    } else {
//                        mView.carownererror(t.message)
//                    }
//                }
//
//                override fun onError(e: Throwable) {
//                    mView.carownererror(e!!.message.toString())
//                }
//            })
        ApiData.ownerone(carid, idname, idnumber, OnSuccessAndFaultSub(object : OnSuccessAndFaultListener {
            override fun onSuccess(result: String?) {
                if (Utils.strinjson(result, "code") == "1") {
                    var getCodeBean = GsonUtils.fromJson(result, GetCodeBean::class.java)
                    mView.carownersuccess(getCodeBean)
                } else {
                    mView.carownererror(Utils.strinjson(result, "message"))
                }
            }

            override fun onFault(errorMsg: String?) {
                mView.carownererror(errorMsg!!)
            }
        }))
    }
}