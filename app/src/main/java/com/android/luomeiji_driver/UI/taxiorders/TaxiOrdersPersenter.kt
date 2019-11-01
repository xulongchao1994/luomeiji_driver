package com.android.luomeiji_driver.UI.taxiorders

import android.content.Context
import android.util.Log
import com.android.luomeiji_driver.Base.LBasePersenter
import com.android.luomeiji_driver.Tools.GsonUtils
import com.android.luomeiji_driver.Tools.Utils
import com.android.luomeiji_driver.bean.TaxiOrdersBean
import com.android.luomeiji_driver.network.ApiData
import com.android.luomeiji_driver.network.OnSuccessAndFaultListener
import com.android.luomeiji_driver.network.OnSuccessAndFaultSub

class TaxiOrdersPersenter(mView: ITaxiOrders, context: Context) : LBasePersenter<ITaxiOrders>(mView, context) {

    fun taxiorderqiang() {
        ApiData.taxiorder(OnSuccessAndFaultSub(object : OnSuccessAndFaultListener {
            override fun onSuccess(result: String?) {
//                if (Utils.strinjson(result, "code") == "1") {
                var taxiordersbean = GsonUtils.fromJson(result, TaxiOrdersBean::class.java)
                mView.taxiorderqiangsuccess(taxiordersbean)
//                } else {
//                    mView.taxiorderqiangerror(Utils.strinjson(result, "message"))
//                }
            }

            override fun onFault(errorMsg: String?) {
                mView.taxiorderqiangerror(errorMsg!!)
            }
        }))
    }

    fun taxiorderdriver(orderid: String, driverid: String, userId: String) {
        ApiData.taxiorderdriver(orderid, driverid, OnSuccessAndFaultSub(object : OnSuccessAndFaultListener {
            override fun onSuccess(result: String?) {
//                if (Utils.strinjson(result, "code") == "1") {
                mView.taxiorderdriversuccess(Utils.strinjson(result, "message"), orderid, userId)
//                } else {
//                    mView.taxiorderdrivererror(Utils.strinjson(result, "message"))
//                }
            }

            override fun onFault(errorMsg: String?) {
                mView.taxiorderdrivererror(errorMsg!!)
            }
        }))
    }
}