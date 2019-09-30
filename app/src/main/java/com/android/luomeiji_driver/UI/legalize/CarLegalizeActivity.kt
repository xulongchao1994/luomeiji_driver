package com.android.luomeiji_driver.UI.legalize

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.util.Log
import android.view.LayoutInflater
import android.widget.DatePicker
import com.android.luomeiji_driver.Base.LBaseAppCompatActivity
import com.android.luomeiji_driver.R
import com.android.luomeiji_driver.Tools.MyToast
import kotlinx.android.synthetic.main.activity_carlegalize.*
import java.util.*


class CarLegalizeActivity : LBaseAppCompatActivity<CarLegalizePersenter>(), ICarLegalizeView {
    override fun carlegalizesuccess(string: String) {
        startActivity(Intent(this, CarLegalize_TwoActivity::class.java))
    }

    override fun carlegalizeerror(string: String) {
        MyToast.showMsg(string)
    }

    override fun initlayout(): Int {
        return R.layout.activity_carlegalize
    }

    var vehicleExpirationTime = ""
    var sp: SharedPreferences? = null
    var carid = ""
    override fun initView() {
        sp = getSharedPreferences("USERINFO", Context.MODE_PRIVATE)
        carid = sp!!.getString("driveruserid", "").toString()
        carlealize_baoxian.setOnClickListener {
            showdataload()
        }
        carlegalize_bt.setOnClickListener {
            var numberPlate = carlegalize_carnumber.text.toString()
            var vehicleModel = carlegalize_cartype.text.toString()
            var vehicleColor = carlegalize_carcolor.text.toString()
            var vehicleName = carlegalize_carname.text.toString()
            if (numberPlate == "") {
                MyToast.showMsg("请输入车牌号")
                return@setOnClickListener
            }
            if (vehicleModel == "") {
                MyToast.showMsg("请输入车牌型号")
                return@setOnClickListener
            }
            if (vehicleColor == "") {
                MyToast.showMsg("请输入车牌颜色")
                return@setOnClickListener
            }
            if (vehicleName == "") {
                MyToast.showMsg("请输入车主姓名")
                return@setOnClickListener
            }
            if (vehicleExpirationTime == "") {
                MyToast.showMsg("请输入车牌保险到期时间")
                return@setOnClickListener
            }
            mPersenter!!.carlegalize(carid, numberPlate, vehicleModel, vehicleColor, vehicleName, vehicleExpirationTime)
        }
    }

    private fun showdataload() {
        val c = Calendar.getInstance()
        var dataload = DatePickerDialog(this, object : DatePickerDialog.OnDateSetListener {
            override fun onDateSet(p0: DatePicker?, p1: Int, p2: Int, p3: Int) {
                Log.d("时间", p1.toString() + "  " + p2.toString() + "  " + p3)
                vehicleExpirationTime = p1.toString() + "-" + p2.toString() + "-" + p3.toString()
            }
        }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH))
        dataload.show()
    }

    override fun initPersenter() {
        mPersenter = CarLegalizePersenter(this, this)
    }

    override fun showlogin() {
    }

    override fun dissmisslogin() {
    }
}