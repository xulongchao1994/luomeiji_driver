package com.android.luomeiji_driver.network

import io.reactivex.observers.DisposableObserver
import okhttp3.RequestBody
import okhttp3.ResponseBody
import java.util.concurrent.ThreadPoolExecutor

object ApiData {
    /**
     * 获取验证码
     */
    fun getcode(code: String, subscriber: DisposableObserver<ResponseBody>) {
        var observable = ApiFactify.getInstance()!!.getcode(code)
        ApiFactify.toSubscribe(observable, subscriber)
    }

    /**
     * 司机认证第一步
     */
    fun ownerone(carid: String, idname: String, idnumber: String, subscriber: DisposableObserver<ResponseBody>) {
        var observable = ApiFactify.getInstance()!!.ownerone(carid, idname, idnumber)
        ApiFactify.toSubscribe(observable, subscriber)
    }

    /**
     * 设置密码
     */
    fun setpsw(phoneNum: String, password: String, verPassword: String, code: String, subscriber: DisposableObserver<ResponseBody>) {
        var observable = ApiFactify.getInstance()!!.signup2(phoneNum, password, verPassword, code)
        ApiFactify.toSubscribe(observable, subscriber)
    }

    /**
     * 登录
     */
    fun login(driverPhoneNum: String, password: String, subscriber: DisposableObserver<ResponseBody>) {
        var observable = ApiFactify.getInstance()!!.login(driverPhoneNum, password)
        ApiFactify.toSubscribe(observable, subscriber)
    }

    /**
     * 修改密码时获取验证码
     */
    fun getfindpswcode(code: String, subscriber: DisposableObserver<ResponseBody>) {
        var observable = ApiFactify.getInstance()!!.getfindpswcode(code)
        ApiFactify.toSubscribe(observable, subscriber)
    }

    /**
     * 修改密码
     */
    fun findpsw(phoneNum: String, password: String, code: String, subscriber: DisposableObserver<ResponseBody>) {
        var observable = ApiFactify.getInstance()!!.findpsw(phoneNum, password, code)
        ApiFactify.toSubscribe(observable, subscriber)
    }

    /**
     * 司机认证第二步
     */
    fun carownertow(body: RequestBody, subscriber: DisposableObserver<ResponseBody>) {
        var observable = ApiFactify.getInstance()!!.carownertwo(body)
        ApiFactify.fromSubscribe(observable, subscriber)
    }

    /**
     * 司机认证第三步
     */
    fun carlegalize(numberPlate: String, vehicleModel: String, vehicleColor: String, vehicleName: String, vehicleExpirationTime: String, driverUserId: String, subscriber: DisposableObserver<ResponseBody>) {
        var observable = ApiFactify.getInstance()!!.carlegalize(driverUserId, numberPlate, vehicleModel, vehicleColor, vehicleName, vehicleExpirationTime)
        ApiFactify.fromSubscribe(observable, subscriber)

    }


    /**
     * 司机认证第二步
     */
    fun carlegalizetow(body: RequestBody, subscriber: DisposableObserver<ResponseBody>) {
        var observable = ApiFactify.getInstance()!!.carlegalizetwo(body)
        ApiFactify.fromSubscribe(observable, subscriber)
    }

    /**
     * 司机抢单列表
     */
    fun taxiorder(subscriber: DisposableObserver<ResponseBody>) {
        var observable = ApiFactify.getInstance()!!.taxiorderqiang()
        ApiFactify.fromSubscribe(observable, subscriber)
    }

    /**
     * 司机抢单
     */
    fun taxiorderdriver(driverOrderId: String, driverUserId: String, subscriber: DisposableObserver<ResponseBody>) {
        var observable = ApiFactify.getInstance()!!.taxiorderdriver(driverOrderId, driverUserId)
        ApiFactify.fromSubscribe(observable, subscriber)
    }

    /**
     * 获取当前认证状态
     */
    fun getlegalize(driverid: String, subscriber: DisposableObserver<ResponseBody>) {
        var observable = ApiFactify.getInstance()!!.getlegalize(driverid)
        ApiFactify.fromSubscribe(observable, subscriber)
    }
}
