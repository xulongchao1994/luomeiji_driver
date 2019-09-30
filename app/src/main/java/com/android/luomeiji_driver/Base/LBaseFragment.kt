package com.android.luomeiji_driver.Base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.android.luomeiji_driver.Tools.LoadingDialog

abstract class LBaseFragment<P : LBasePersenter<*>> : Fragment() {
    protected var mPersenter: P? = null
    protected abstract fun initPersenter()
    protected abstract fun initLayout(): Int
    protected var mView: View? = null
    protected abstract fun initViews(view: View)
    protected var mLoading: LoadingDialog? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        initPersenter()
        mView = inflater.inflate(initLayout(), container, false)
        mLoading = LoadingDialog(activity)
        mLoading!!.setCanceledOnTouchOutside(false)
        return mView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
    }

    override fun onDestroy() {
        super.onDestroy()
        mPersenter!!.cannelRequest()
    }
}