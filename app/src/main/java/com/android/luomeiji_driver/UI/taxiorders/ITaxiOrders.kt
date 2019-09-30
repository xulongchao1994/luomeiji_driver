package com.android.luomeiji_driver.UI.taxiorders

import com.android.luomeiji_driver.Base.LBaseView
import com.android.luomeiji_driver.bean.TaxiOrdersBean

interface ITaxiOrders : LBaseView {
    //司机抢单列表
    fun taxiorderqiangsuccess(string: TaxiOrdersBean)

    fun taxiorderqiangerror(string: String)

    //司机抢单
    fun taxiorderdriversuccess(string: String)

    fun taxiorderdrivererror(string: String)
}