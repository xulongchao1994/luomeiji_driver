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
                if (Utils.strinjson(result, "code") == "1") {
                    Log.d("司机抢单_列表", result)
                    var taxiordersbean = GsonUtils.fromJson(result, TaxiOrdersBean::class.java)
                    mView.taxiorderqiangsuccess(taxiordersbean)
                } else {
                    mView.taxiorderqiangerror(Utils.strinjson(result, "message"))
                }
            }

            override fun onFault(errorMsg: String?) {
                Log.d("司机抢单——列表error", errorMsg)
                mView.taxiorderqiangerror(errorMsg!!)
            }
        }))
    }

    fun taxiorderdriver(orderid: String, driverid: String) {
        ApiData.taxiorderdriver(orderid, "2", OnSuccessAndFaultSub(object : OnSuccessAndFaultListener {
            override fun onSuccess(result: String?) {
                Log.d("司机抢单——抢", result)
                if (Utils.strinjson(result, "code") == "1") {
                    mView.taxiorderdriversuccess(Utils.strinjson(result, "message"))
                } else {
                    mView.taxiorderdrivererror(Utils.strinjson(result, "message"))
                }
            }

            override fun onFault(errorMsg: String?) {
                mView.taxiorderdrivererror(errorMsg!!)
                Log.d("司机抢单——抢", errorMsg)
            }
        }))
    }
}