package com.android.luomeiji_driver.UI.order

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.android.luomeiji_driver.R
import com.android.luomeiji_driver.adapter.Order_Viewpage_Adapter
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_order.*

class OrderActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)
        initview()
    }

    var fragmentlist = arrayListOf<Fragment>()
    var titlelist = arrayListOf<String>("全部订单", "网约订单", "线路订单")
    private fun initview() {
        for (i in 0 until titlelist.size) {
            var fragmentitem = OrderFragment()
            var bundle = Bundle()
            bundle.putString("ordertype", titlelist[i])
            fragmentitem.arguments = bundle
            fragmentlist.add(fragmentitem)
        }
        var fragmentadapter = Order_Viewpage_Adapter(supportFragmentManager, fragmentlist)
        order_viewpager.adapter = fragmentadapter
        order_tab.setupWithViewPager(order_viewpager)
        for (i in 0 until titlelist.size) {
            var tab = order_tab.getTabAt(i)
            tab!!.setCustomView(R.layout.order_tab_item)
            var item_text = tab.customView!!.findViewById<TextView>(R.id.order_teb_item_text)
            item_text.text = titlelist[i]
            if (i == 0) {
                item_text.setTextColor(resources.getColor(R.color.ordertext_true))
            }
        }
        order_tab.setOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(p0: TabLayout.Tab?) {
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                tab!!.customView!!.findViewById<TextView>(R.id.order_teb_item_text)
                    .setTextColor(resources.getColor(R.color.ordertext_false))

            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab!!.customView!!.findViewById<TextView>(R.id.order_teb_item_text)
                    .setTextColor(resources.getColor(R.color.ordertext_true))
                order_viewpager.currentItem = tab.position
            }
        })
    }
}