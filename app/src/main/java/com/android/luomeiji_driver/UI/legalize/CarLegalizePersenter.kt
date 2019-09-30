package com.android.luomeiji_driver.UI.legalize

import android.content.Context
import com.android.luomeiji_driver.Base.LBasePersenter
import com.android.luomeiji_driver.Tools.Utils
import com.android.luomeiji_driver.network.ApiData
import com.android.luomeiji_driver.network.OnSuccessAndFaultListener
import com.android.luomeiji_driver.network.OnSuccessAndFaultSub

class CarLegalizePersenter(mView: ICarLegalizeView, context: Context) :
    LBasePersenter<ICarLegalizeView>(mView, context) {
    fun carlegalize(numberPlate: String, vehicleModel: String, vehicleColor: String, vehicleName: String, vehicleExpirationTime: String, driverUserId: String) {
        ApiData.carlegalize(numberPlate, vehicleModel, vehicleColor, vehicleName, vehicleExpirationTime, driverUserId, OnSuccessAndFaultSub(object : OnSuccessAndFaultListener {
            override fun onSuccess(result: String?) {
                if (Utils.strinjson(result, "code") == "1") {
                    mView.carlegalizesuccess(Utils.strinjson(result, "message"))
                } else {
                    mView.carlegalizeerror(Utils.strinjson(result, "message"))
                }
            }

            override fun onFault(errorMsg: String?) {
                mView.carlegalizeerror(errorMsg!!)
            }
        }))
    }
}