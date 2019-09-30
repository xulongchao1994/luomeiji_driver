package com.android.luomeiji_driver.UI.passenger

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.luomeiji_driver.Base.LBaseAppCompatActivity
import com.android.luomeiji_driver.R
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import kotlinx.android.synthetic.main.activity_passenger.*

class PassengerActivity : LBaseAppCompatActivity<PassengerPersenter>(), IPassengerView {
    override fun initlayout(): Int {
        return R.layout.activity_passenger
    }

    override fun initView() {
        var linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.orientation = RecyclerView.VERTICAL
        passenger_reycler.layoutManager = linearLayoutManager
        var stringlist = arrayListOf<String>()
        for (i in 0..10) {
            if (i / 2 == 0) {
                stringlist.add("本次体验还不错，就是司机有点墨迹，路上来的太慢，加油！")
            } else {
                stringlist.add("本次体验还不错，就是司机有点墨迹，路上来的太慢，加油！本次体验还不错，就是司机有点墨迹，路上来的太慢，加油")
            }
        }
        var adapter =
            object : BaseQuickAdapter<String, BaseViewHolder>(R.layout.adapter_passengeritem) {
                override fun convert(helper: BaseViewHolder?, item: String?) {
                    helper!!.setText(R.id.adapter_passenger_text, item!!)
                }
            }
        passenger_reycler.adapter = adapter
        adapter.setNewData(stringlist)
        passenger_swipe.setOnRefreshListener {
            passenger_swipe.isRefreshing = false
        }
    }

    override fun initPersenter() {
        mPersenter = PassengerPersenter(this, this)
    }

    override fun showlogin() {
    }

    override fun dissmisslogin() {
    }
}