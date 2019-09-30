package com.android.luomeiji_driver.Base

import android.content.Context
import com.android.luomeiji_driver.network.Api
import com.android.luomeiji_driver.network.ApiFactify
import io.reactivex.disposables.Disposable
import rx.Observable
import rx.Subscription

abstract class LBasePersenter<V : LBaseView>(var mView: V, var mContext: Context) {
    protected var mApi: Api? = null
    protected var mDisposable: Disposable? = null

    init {
        if (mApi == null) {
            mApi = ApiFactify.getInstance()
        }
    }

    /**
     * 取消网络请求(这里是有RxJava,即为取消订阅结果)
     */

    fun cannelRequest() {
//        if (mSubscription != null)
//            mSubscription!!.unsubscribe()
        if (mDisposable != null && !mDisposable!!.isDisposed)
            mDisposable!!.dispose()
    }
}