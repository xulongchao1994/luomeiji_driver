package com.android.luomeiji_driver.UI.taxiorders

import android.content.Intent
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.luomeiji_driver.Base.LBaseAppCompatActivity
import com.android.luomeiji_driver.R
import com.android.luomeiji_driver.UI.withpassenger.WithPassengerActivity
import com.android.luomeiji_driver.bean.TaxiOrdersBean
import com.android.luomeiji_driver.data.UserData_Java
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.vondear.rxtool.view.RxToast
import kotlinx.android.synthetic.main.activity_taxiorders.*

class TaxiOrdersActivity : LBaseAppCompatActivity<TaxiOrdersPersenter>(), ITaxiOrders {
    override fun taxiorderdriversuccess(string: String, orderid: String, userId: String) {
        startActivity(Intent(this, WithPassengerActivity::class.java).putExtra("orderid", orderid).putExtra("userId", userId))
    }

    override fun taxiorderdrivererror(string: String) {
        RxToast.normal("抢单失败")
    }

    override fun taxiorderqiangsuccess(string: TaxiOrdersBean) {
        taxiadapter!!.setNewData(string.data)
        taxiadapter!!.setOnItemClickListener { adapter, view, position ->
            Log.d("司机接单", string.data[position].driverOrderId + "     " + UserData_Java.driveruserid)
            mPersenter!!.taxiorderdriver(string.data[position].driverOrderId, UserData_Java.driveruserid, string.data[position].userId)
        }
        taxiorders_swiperefresh.isRefreshing = false
    }

    override fun taxiorderqiangerror(string: String) {
    }

    override fun initlayout(): Int {
        return R.layout.activity_taxiorders
    }

    var taxiadapter: BaseQuickAdapter<TaxiOrdersBean.DataBean, BaseViewHolder>? = null
    override fun initView() {
        taxiadapter = object : BaseQuickAdapter<TaxiOrdersBean.DataBean, BaseViewHolder>(R.layout.adapter_taxiorder_item) {
            override fun convert(helper: BaseViewHolder?, item: TaxiOrdersBean.DataBean?) {
                helper!!.setText(R.id.taxiorder_item_chengke, item!!.departure)
                    .setText(R.id.taxiorder_item_mudidi, item!!.destination)
            }
        }
        var linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.orientation = RecyclerView.VERTICAL
        taxiorder_recycler.layoutManager = linearLayoutManager
        taxiorder_recycler.adapter = taxiadapter
        taxiorders_swiperefresh.setOnRefreshListener {
            mPersenter!!.taxiorderqiang()
        }
        mPersenter!!.taxiorderqiang()
    }

    override fun initPersenter() {
        mPersenter = TaxiOrdersPersenter(this, this)

    }

    override fun showlogin() {
        mLoading!!.show()
    }

    override fun dissmisslogin() {
        mLoading!!.dismiss()
    }
}