package com.android.luomeiji_driver.UI.legalizeresult

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.android.luomeiji_driver.R
import com.android.luomeiji_driver.UI.main.MainActivity
import com.android.luomeiji_driver.network.MyOkHttp
import kotlinx.android.synthetic.main.activity_legalizeresultwith.*
import okhttp3.FormBody
import okhttp3.Request
import java.lang.Exception

class LegalizeresultwithActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_legalizeresultwith)
        legalizeresultwith_bt.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
//        var body = FormBody.Builder().build()
//        MyOkHttp.getInstance()
//            .post("http://192.168.1.3:8088/t/t1", body, object : MyOkHttp.RequestCallBack {
//                override fun success(data: String?) {
//                    Log.d("main", data!!)
//                }
//
//                override fun fail(request: Request?, e: Exception?) {
//                    Log.d("main", e.toString())
//                }
//            })
    }
}