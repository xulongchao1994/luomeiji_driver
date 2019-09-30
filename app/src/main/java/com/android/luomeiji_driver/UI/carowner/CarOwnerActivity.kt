package com.android.luomeiji_driver.UI.carowner

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import com.android.luomeiji_driver.Base.LBaseAppCompatActivity
import com.android.luomeiji_driver.R
import com.android.luomeiji_driver.Tools.MyToast
import com.android.luomeiji_driver.network.GetCodeBean
import kotlinx.android.synthetic.main.activity_carowner.*

class CarOwnerActivity : LBaseAppCompatActivity<CarOwnerPersenter>(), ICarOwnerView {
    override fun carownersuccess(getCodeBean: GetCodeBean) {
        var intent = Intent(this, CarOwnerTwoActivity::class.java)
        intent.putExtra("name", name_str)
        intent.putExtra("idcar", idcar_str)
        startActivity(intent)
    }

    override fun carownererror(string: String) {
        MyToast.showMsg(string)
    }

    override fun initlayout(): Int {
        return R.layout.activity_carowner
    }

    override fun initView() {
        carowner_next_bt.setOnClickListener {
            isnext()
        }
    }

    var name_str = ""
    var idcar_str = ""
    var carid = ""
    var sp: SharedPreferences? = null
    private fun isnext() {
        sp = getSharedPreferences("USERINFO", Context.MODE_PRIVATE)
        carid = sp!!.getString("driveruserid", "").toString()
        name_str = carowner_name_et.text.toString()
        idcar_str = carowner_idcarname_et.text.toString()
        if (name_str == "") {
            MyToast.showMsg("请输入姓名")
            return
        }
        if (idcar_str == "") {
            MyToast.showMsg("请输入身份证号")
            return
        }
        mPersenter!!.owner(carid, name_str, idcar_str)
    }

    override fun initPersenter() {
        mPersenter = CarOwnerPersenter(this, this)
    }

    override fun showlogin() {
    }

    override fun dissmisslogin() {
    }
}