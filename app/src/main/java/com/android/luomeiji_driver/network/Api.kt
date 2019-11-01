package com.android.luomeiji_driver.network

import com.android.luomeiji_driver.bean.SignUpBean
import com.android.luomeiji_driver.network.GetCodeBean
import io.reactivex.Observable
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST


interface Api {
    /**
     * 获取验证码
     */
    @FormUrlEncoded
    @POST("sms/getRegCode")
    fun getcode(@Field("phoneNum") phoneNum: String): Observable<ResponseBody>

    /**
     * 获取验证码
     */
    @FormUrlEncoded
    @POST("sms/getRegCode")
    fun getcode2(@Field("phoneNum") phoneNum: String): Observable<GetCodeBean>

    /**
     * 注册
     */
    @FormUrlEncoded
    @POST("driverUser/registered")
    fun signup(
        @Field("phoneNum") phoneNum: String
        , @Field("password") password: String
        , @Field("verPassword") verPassword: String
        , @Field("code") code: String
    ): Observable<SignUpBean>

    @FormUrlEncoded
    @POST("driverUser/registered")
    fun signup2(
        @Field("phoneNum") phoneNum: String
        , @Field("password") password: String
        , @Field("verPassword") verPassword: String
        , @Field("code") code: String
    ): Observable<ResponseBody>

    /**
     * 认证第一步
     */
//    @FormUrlEncoded
//    @POST("driverUser/androidDriverVerify")
//    fun ownerone(
//        @Field("driverUserId") driverUserId: String
//        , @Field("idName") idName: String
//        , @Field("idNum") idNum: String
//    ): Observable<GetCodeBean>
    @FormUrlEncoded
    @POST("driverUser/androidDriverVerify")
    fun ownerone(
        @Field("driverUserId") driverUserId: String
        , @Field("idName") idName: String
        , @Field("idNum") idNum: String
    ): Observable<ResponseBody>


    /**
     * 登录
     */
    @FormUrlEncoded
    @POST("driverUser/driverUserLogin")
    fun login(
        @Field("driverPhoneNum") driverPhoneNum: String
        , @Field("password") password: String
    ): Observable<ResponseBody>

    /**
     * 修改密码时获取验证码
     */
    @FormUrlEncoded
    @POST("sms/getUpdatePasswordCode")
    fun getfindpswcode(@Field("phoneNum") phoneNum: String): Observable<ResponseBody>

    /**
     * 修改密码
     */
    @FormUrlEncoded
    @POST("driverUser/driverUpdatePad")
    fun findpsw(
        @Field("phoneNum") phoneNum: String
        , @Field("password") password: String
        , @Field("code") code: String
    ): Observable<ResponseBody>

    /**
     * 司机认证第二步
     */
    @POST("driverUser/androidDriverVerifyIdFile")
    fun carownertwo(@Body body: RequestBody): Observable<ResponseBody>

    /**
     * 司机认证第三步
     */
    @FormUrlEncoded
    @POST("driverUser/androidCarInf")
    fun carlegalize(
        @Field("driverUserId") driverUserId: String
        , @Field("numberPlate") numberPlate: String
        , @Field("vehicleModel") vehicleModel: String
        , @Field("vehicleColor") vehicleColor: String
        , @Field("vehicleName") vehicleName: String
        , @Field("vehicleExpirationTime") vehicleExpirationTime: String
    ): Observable<ResponseBody>

    /**
     * 司机认证第四步
     */
    @POST("driverUser/androidCarInfFile")
    fun carlegalizetwo(@Body body: RequestBody): Observable<ResponseBody>

    /**
     * 网约车抢单列表
     */
    @POST("driverOrder/driverOrderList")
    fun taxiorderqiang(): Observable<ResponseBody>

    /**
     * 网约车司机抢单
     * driverOrderId 订单id
     * driverUserId 司机id
     */
    @FormUrlEncoded
    @POST("driverOrder/driverUserAddOrder")
    fun taxiorderdriver(
        @Field("driverOrderId") vehicleColor: String
        , @Field("driverUserId") vehicleName: String
    ): Observable<ResponseBody>

    /**
     * 获取认证状态
     */

    @FormUrlEncoded
    @POST("driverUser/isDriverUserReview")
    fun getlegalize(@Field("driverUserId") driverUserId: String): Observable<ResponseBody>

    /**
     * 司机发送位置
     */
    @FormUrlEncoded
    @POST("driverOrder/setLocation")
    fun postdriverlocation(
        @Field("driverOrderId") driverOrderId: String,
        @Field("driverUserLot") driverUserLot: String,
        @Field("driverUserLat") driverUserLat: String,
        @Field("userId") userId: String
    ): Observable<ResponseBody>

    /**
     * 司机发车
     * driverOrderId  订单id
     * code  代状态    2001 已发车 2002司机已送达  2003已送达
     */
    @FormUrlEncoded
    @POST("driverOrder/updateState")
    fun fache(@Field("driverOrderId") driverOrderId: String, @Field("code") code: String): Observable<ResponseBody>
}