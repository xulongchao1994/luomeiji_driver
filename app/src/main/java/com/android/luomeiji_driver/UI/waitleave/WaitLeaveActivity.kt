package com.android.luomeiji_driver.UI.waitleave

import android.app.Dialog
import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.android.luomeiji_driver.Base.LBaseAppCompatActivity
import com.android.luomeiji_driver.R
import kotlinx.android.synthetic.main.activity_waitleave.*

class WaitLeaveActivity : LBaseAppCompatActivity<WaitLeavePersenter>(), IWaitleaveView {
    override fun initlayout(): Int {
        return R.layout.activity_waitleave
    }

    override fun initView() {
        waitleave_quxiao.setOnClickListener {
            showload()
        }
    }

    private fun showload() {
        var upload = Dialog(this, R.style.ActionSheetDialogStyle)
        var view = LayoutInflater.from(this).inflate(R.layout.view_quxiaoxingcheng, null)
        var button_string = view.findViewById<Button>(R.id.view_quixao)
        button_string.setOnClickListener {
            if (upload != null && upload.isShowing) {
                upload.dismiss()
            }
        }
        upload.setContentView(view)
        //获取当前Activity所在的窗体
        var dialogWindow = upload.getWindow()
        //设置Dialog从窗体底部弹出
        dialogWindow!!.setGravity(Gravity.CENTER)
        //获得窗体的属性
        var lp = dialogWindow.getAttributes()
        lp.width = ViewGroup.LayoutParams.MATCH_PARENT
        lp.y = 20;//设置Dialog距离底部的距离
//       将属性设置给窗体
        dialogWindow!!.setAttributes(lp)
        upload.show()
    }

    override fun initPersenter() {
        mPersenter = WaitLeavePersenter(this, this)
    }

    override fun showlogin() {
        mLoading!!.show()
    }

    override fun dissmisslogin() {
        mLoading!!.dismiss()
    }
}