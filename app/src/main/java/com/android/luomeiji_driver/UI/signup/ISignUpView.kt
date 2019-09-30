package com.android.luomeiji_driver.UI.signup

import com.android.luomeiji_driver.Base.LBaseView

interface ISignUpView : LBaseView {
    fun getcodesuccess(code: String)
    fun getcodeerror(error: String)
}