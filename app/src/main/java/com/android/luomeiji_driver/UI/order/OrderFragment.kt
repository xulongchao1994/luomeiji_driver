package com.android.luomeiji_driver.UI.order

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.luomeiji_driver.Base.LBaseFragment
import com.android.luomeiji_driver.R
import com.android.luomeiji_driver.bean.OrderItemBean
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import kotlinx.android.synthetic.main.fragment_order.*

class OrderFragment : LBaseFragment<OrderFragmentPersenter>(), IOrderFragmnetView {
    override fun initPersenter() {
        mPersenter = OrderFragmentPersenter(this, activity!!)
    }

    override fun initLayout(): Int {
        return R.layout.fragment_order
    }

    var ordertype = ""
    override fun initViews(view: View) {
        ordertype = arguments!!.getString("ordertype", "")
        var itemlist = arrayListOf<OrderItemBean>()
        for (i in 0..10) {
            itemlist.add(OrderItemBean(ordertype, "没有目的"))
        }
        var adapter =
            object : BaseQuickAdapter<OrderItemBean, BaseViewHolder>(R.layout.adapter_orderitem) {
                override fun convert(helper: BaseViewHolder?, item: OrderItemBean?) {
                    helper!!.setText(R.id.order_item_chufadi, item!!.chufadi)
                        .setText(R.id.order_item_mudidi, item!!.mudidi)
                }
            }
        var linearLayoutManager = LinearLayoutManager(activity!!)
        linearLayoutManager.orientation = RecyclerView.VERTICAL
        orderfragment_recycler.layoutManager = linearLayoutManager
        orderfragment_recycler.adapter = adapter
        adapter.setNewData(itemlist)
    }

    override fun showlogin() {
    }

    override fun dissmisslogin() {
    }
}