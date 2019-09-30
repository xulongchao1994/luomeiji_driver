package com.android.luomeiji_driver.UI.login

import com.android.luomeiji_driver.Base.LBaseView
import com.android.luomeiji_driver.bean.LoginBean

interface ILoginView : LBaseView {
    fun loginsuccess(string: LoginBean)
    fun loginerror(string: String)
}