package com.android.luomeiji_driver.UI.carowner

import com.android.luomeiji_driver.Base.LBaseView
import com.android.luomeiji_driver.network.GetCodeBean

interface ICarOwnerView : LBaseView {

    fun carownersuccess(getCodeBean: GetCodeBean)
    fun carownererror(string: String)
}