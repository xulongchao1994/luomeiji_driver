package com.android.luomeiji_driver.UI.carowner

import android.content.Context
import com.android.luomeiji_driver.Base.LBasePersenter
import com.android.luomeiji_driver.Tools.GsonUtils
import com.android.luomeiji_driver.Tools.Utils
import com.android.luomeiji_driver.network.ApiData
import com.android.luomeiji_driver.network.GetCodeBean
import com.android.luomeiji_driver.network.OnSuccessAndFaultListener
import com.android.luomeiji_driver.network.OnSuccessAndFaultSub
import okhttp3.FormBody
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

class CarOwnerTwoPersenter(mView: ICarOwnerTwoView, context: Context) :
    LBasePersenter<ICarOwnerTwoView>(mView, context) {
    fun carownertwo(idFrontUrl: File, idReverseUrl: File, handheldIdentityUrl: File, driverLicenseUrl: File, driverUserId: String) {
        var bodys = MultipartBody.Builder()
            .addFormDataPart(
                "idFrontUrl", idFrontUrl.name, RequestBody.create(MediaType.parse("image/*"), idFrontUrl)
            )
            .addFormDataPart(
                "idReverseUrl", idReverseUrl.name, RequestBody.create(MediaType.parse("image/*"), idReverseUrl)
            )
            .addFormDataPart(
                "handheldIdentityUrl", handheldIdentityUrl.name, RequestBody.create(MediaType.parse("image/*"), handheldIdentityUrl)
            )
            .addFormDataPart(
                "driverLicenseUrl", driverLicenseUrl.name, RequestBody.create(MediaType.parse("image/*"), driverLicenseUrl)
            )
            .addFormDataPart(
                "driverUserId", driverUserId
            ).build()
        ApiData!!.carownertow(bodys, OnSuccessAndFaultSub(object : OnSuccessAndFaultListener {
            override fun onSuccess(result: String?) {
                if (Utils.strinjson(result, "code") == "1") {
                    var getCodeBean = GsonUtils.fromJson(result, GetCodeBean::class.java)
                    mView.carownertwosuccess(getCodeBean)
                } else {
                    mView.carownertwoerror(Utils.strinjson(result, "message"))
                }
            }

            override fun onFault(errorMsg: String?) {
                mView.carownertwoerror(errorMsg!!)
            }
        }))
    }

}