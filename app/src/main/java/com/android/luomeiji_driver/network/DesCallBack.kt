package com.android.luomeiji_driver.network

interface DesCallBack<T> {
    fun success(any: T)
    fun failed(e: Throwable)
}