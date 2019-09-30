package com.android.luomeiji_driver.UI.taxiorders

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.luomeiji_driver.Base.LBaseAppCompatActivity
import com.android.luomeiji_driver.R
import com.android.luomeiji_driver.bean.TaxiOrdersBean
import com.android.luomeiji_driver.data.UserData_Java
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import kotlinx.android.synthetic.main.activity_taxiorders.*

class TaxiOrdersActivity : LBaseAppCompatActivity<TaxiOrdersPersenter>(), ITaxiOrders {
    override fun taxiorderdriversuccess(string: String) {

    }

    override fun taxiorderdrivererror(string: String) {
    }

    override fun taxiorderqiangsuccess(string: TaxiOrdersBean) {
        taxiadapter!!.setNewData(string.data)
        taxiadapter!!.setOnItemClickListener { adapter, view, position ->
            mPersenter!!.taxiorderdriver(string.data[position].driverOrderId, UserData_Java.driveruserid)
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