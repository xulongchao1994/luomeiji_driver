package com.android.luomeiji_driver.UI.withpassenger

import com.android.luomeiji_driver.Base.LBaseView

interface IWihtPassengerView : LBaseView {
    fun driverlocationsuccess(string: String)
    fun driverlocationerror(string: String)

    fun fachesuccess(string: String)
    fun facheerror(string: String)
}