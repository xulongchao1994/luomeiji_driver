package com.android.luomeiji_driver.UI.welcome

import com.android.luomeiji_driver.Base.LBaseView

interface IWelcomeView : LBaseView {
    //获取当前认证状态
    fun getlegalizesuccess(string: String)

    fun getlegalizeerror(string: String)
}