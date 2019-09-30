package com.android.luomeiji_driver.UI.legalize

import android.content.Context
import com.android.luomeiji_driver.Base.LBasePersenter
import com.android.luomeiji_driver.Tools.Utils
import com.android.luomeiji_driver.network.ApiData
import com.android.luomeiji_driver.network.OnSuccessAndFaultListener
import com.android.luomeiji_driver.network.OnSuccessAndFaultSub
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

class CarLegalize_TwoPersenter(mView: ICarLegalize_TwoView, context: Context) :
    LBasePersenter<ICarLegalize_TwoView>(mView, context) {

    fun carlegalize_two(driverUserId: String, frontOfTheCarUrl: File, carPurchaseUrl: File, carInsuranceUrl: File) {
        var bodys = MultipartBody.Builder()
            .addFormDataPart(
                "frontOfTheCarUrl", frontOfTheCarUrl.name, RequestBody.create(MediaType.parse("image/*"), frontOfTheCarUrl)
            )
            .addFormDataPart(
                "carPurchaseUrl", carPurchaseUrl.name, RequestBody.create(MediaType.parse("image/*"), carPurchaseUrl)
            )
            .addFormDataPart(
                "carInsuranceUrl", carInsuranceUrl.name, RequestBody.create(MediaType.parse("image/*"), carInsuranceUrl)
            )
            .addFormDataPart(
                "driverUserId", driverUserId
            ).build()
        ApiData.carlegalizetow(bodys, OnSuccessAndFaultSub(object : OnSuccessAndFaultListener {
            override fun onSuccess(result: String?) {
                if (Utils.strinjson(result, "code") == "1") {
                    mView.carlegalizetwosuccess(Utils.strinjson(result, "message"))
                } else {
                    mView.carlegalizetwoerror(Utils.strinjson(result, "message"))
                }
            }

            override fun onFault(errorMsg: String?) {
                mView.carlegalizetwoerror(errorMsg!!)
            }
        }))
    }
}