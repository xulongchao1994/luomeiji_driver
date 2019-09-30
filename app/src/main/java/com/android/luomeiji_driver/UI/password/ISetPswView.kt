package com.android.luomeiji_driver.UI.password

import com.android.luomeiji_driver.Base.LBaseView
import com.android.luomeiji_driver.bean.SignUpBean

interface ISetPswView : LBaseView {
    fun singupsuccess(signUpBean: SignUpBean)
    fun signuperror(string: String)
}