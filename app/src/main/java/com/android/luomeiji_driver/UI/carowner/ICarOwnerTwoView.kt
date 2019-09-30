package com.android.luomeiji_driver.UI.carowner

import com.android.luomeiji_driver.Base.LBaseView
import com.android.luomeiji_driver.network.GetCodeBean

interface ICarOwnerTwoView : LBaseView {
    fun carownertwosuccess(getCodeBean: GetCodeBean)
    fun carownertwoerror(string: String)
}