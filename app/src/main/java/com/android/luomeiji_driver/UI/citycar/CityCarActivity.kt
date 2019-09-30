package com.android.luomeiji_driver.UI.citycar

import com.android.luomeiji_driver.Base.LBaseAppCompatActivity
import com.android.luomeiji_driver.R

class CityCarActivity : LBaseAppCompatActivity<CityCarPersenter>(), ICityCarView {
    override fun initlayout(): Int {
        return R.layout.activity_citycar
    }

    override fun initView() {
    }

    override fun initPersenter() {
        mPersenter = CityCarPersenter(this, this)
    }

    override fun showlogin() {
        mLoading!!.show()
    }

    override fun dissmisslogin() {
        mLoading!!.dismiss()
    }
}