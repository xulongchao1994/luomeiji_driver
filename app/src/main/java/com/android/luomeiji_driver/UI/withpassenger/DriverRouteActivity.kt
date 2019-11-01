package com.android.luomeiji_driver.UI.withpassenger

import com.android.luomeiji_driver.Base.LBaseAppCompatActivity
import com.android.luomeiji_driver.R


class DriverRouteActivity : LBaseAppCompatActivity<WithPassengerPersenter>(), IWihtPassengerView {
    override fun initlayout(): Int {
        return R.layout.activity_withassenger
    }

    override fun initView() {
    }

    override fun initPersenter() {
        mPersenter = WithPassengerPersenter(this, this)
    }

    override fun driverlocationsuccess(string: String) {
    }

    override fun driverlocationerror(string: String) {
    }

    override fun fachesuccess(string: String) {
    }

    override fun facheerror(string: String) {
    }

    override fun showlogin() {
        mLoading!!.show()
    }

    override fun dissmisslogin() {
        mLoading!!.dismiss()
    }
}
