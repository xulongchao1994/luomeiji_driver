package com.android.luomeiji_driver.UI.choosepassenger

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.luomeiji_driver.Base.LBaseAppCompatActivity
import com.android.luomeiji_driver.R
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import kotlinx.android.synthetic.main.activity_chooseepassenger.*

class ChoosePassengerActivity : LBaseAppCompatActivity<ChoosePassengerPersenter>(),
    IChoosePassengerView {
    override fun initlayout(): Int {
        return R.layout.activity_chooseepassenger
    }

    override fun initView() {
        var stringlist = arrayListOf<String>()
        for (i in 0..15) {
            stringlist.add("张先生$i")
        }
        var linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.orientation = RecyclerView.VERTICAL
        choosepassenger_recycler.layoutManager = linearLayoutManager
        var adapter = object :
            BaseQuickAdapter<String, BaseViewHolder>(R.layout.adapter_choosepassenger_item) {
            override fun convert(helper: BaseViewHolder?, item: String?) {
                helper!!.setText(R.id.adapter_name, item!!)
            }
        }
        choosepassenger_recycler.adapter = adapter
        adapter.setNewData(stringlist)
    }

    override fun initPersenter() {
        mPersenter = ChoosePassengerPersenter(this, this)
    }

    override fun showlogin() {
        mLoading!!.show()
    }

    override fun dissmisslogin() {
        mLoading!!.dismiss()
    }
}