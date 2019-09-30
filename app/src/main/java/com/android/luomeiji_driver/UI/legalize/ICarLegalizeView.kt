package com.android.luomeiji_driver.UI.legalize

import com.android.luomeiji_driver.Base.LBaseView

interface ICarLegalizeView : LBaseView {
    fun carlegalizesuccess(string: String)
    fun carlegalizeerror(string: String)
}