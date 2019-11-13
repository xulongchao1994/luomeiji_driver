package com.android.luomeiji_driver.UI.withpassenger

import android.content.Context
import android.util.Log
import com.android.luomeiji_driver.Base.LBasePersenter
import com.android.luomeiji_driver.Tools.Utils
import com.android.luomeiji_driver.network.ApiData
import com.android.luomeiji_driver.network.OnSuccessAndFaultListener
import com.android.luomeiji_driver.network.OnSuccessAndFaultSub

class WithPassengerPersenter(mView: IWihtPassengerView, context: Context) : LBasePersenter<IWihtPassengerView>(mView, context) {

    fun postdriverlocation(driverOrderId: String, driverUserLot: String, driverUserLat: String, userId: String) {
        Log.d("等待乘客上车!", driverOrderId + "   " + driverUserLot + "   " + driverUserLat)
        ApiData.postdriverlocation(driverOrderId, driverUserLot, driverUserLat, userId, OnSuccessAndFaultSub(object : OnSuccessAndFaultListener {
            override fun onSuccess(result: String?) {
                Log.d("等待乘客上车!", result)
//                if (Utils.strinjson(result, "code") == "1") {
                mView.driverlocationsuccess(Utils.strinjson(result, "message"))
//                } else {
//                    mView.driverlocationerror(Utils.strinjson(result, "message"))
//                }
            }

            override fun onFault(errorMsg: String?) {
                Log.d("等待乘客上车!_error", errorMsg)
                mView.driverlocationerror(errorMsg!!)
            }
        }))
    }

    fun fache(driverOrderId: String, code: String) {
        ApiData.fache(driverOrderId, code, OnSuccessAndFaultSub(object : OnSuccessAndFaultListener {
            override fun onSuccess(result: String?) {
                Log.d("等待乘客上车_发车", result)
                if (Utils.strinjson(result, "code") == "1") {
                    mView.fachesuccess(Utils.strinjson(result, "message"))
                } else {
                    mView.facheerror(Utils.strinjson(result, "message"))
                }
            }

            override fun onFault(errorMsg: String?) {
                mView.facheerror(errorMsg!!)
            }
        }))
    }


}