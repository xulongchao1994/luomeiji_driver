package com.android.luomeiji_driver.UI.withpassenger

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.util.Log
import com.amap.api.location.AMapLocation
import com.amap.api.location.AMapLocationClient
import com.amap.api.location.AMapLocationClientOption
import com.amap.api.location.AMapLocationListener
import com.amap.api.maps.AMap
import com.amap.api.maps.CameraUpdateFactory
import com.amap.api.maps.LocationSource
import com.amap.api.maps.UiSettings
import com.amap.api.maps.model.BitmapDescriptorFactory
import com.amap.api.maps.model.CameraPosition
import com.amap.api.maps.model.LatLng
import com.amap.api.maps.model.MyLocationStyle
import com.android.luomeiji_driver.Base.LBaseAppCompatActivity
import com.android.luomeiji_driver.R
import com.android.luomeiji_driver.data.UserData_Java
import kotlinx.android.synthetic.main.activity_withassenger.*
import android.os.Build
import android.os.SystemClock
import androidx.annotation.RequiresApi
import com.vondear.rxtool.view.RxToast


@RequiresApi(Build.VERSION_CODES.N)
class WithPassengerActivity : LBaseAppCompatActivity<WithPassengerPersenter>(), IWihtPassengerView, LocationSource,
    AMap.OnCameraChangeListener, AMapLocationListener {
    override fun fachesuccess(string: String) {
        RxToast.normal("开始前往目的地")
    }

    override fun facheerror(string: String) {
    }

    var time_isstart = false
    override fun driverlocationsuccess(string: String) {
        //开始等待乘客
        //开始计时
        if (!time_isstart) {
            withassenger_timer.base = SystemClock.elapsedRealtime()
            withassenger_timer.isCountDown = false
            withassenger_timer.start()
            time_isstart = true
        }
    }

    override fun driverlocationerror(string: String) {
        //司机发送位置失败，重新定位
    }

    var aMap: AMap? = null
    var bundle: Bundle? = null
    var lonitude_s = ""
    var latitude_s = ""
    override fun onLocationChanged(p0: AMapLocation?) {
        if (mListener != null && p0 != null) {
            if (p0 != null && p0.getErrorCode() == 0) {
                mListener!!.onLocationChanged(p0)// 显示系统小蓝点
                aMap!!.moveCamera(
                    CameraUpdateFactory
                        .newLatLngZoom(LatLng(p0.latitude, p0.longitude), 17f)
                )// 设置指定的可视区域地图
                lonitude_s = p0.longitude.toString()
                latitude_s = p0.latitude.toString()
                mPersenter!!.postdriverlocation(orderid, lonitude_s, latitude_s, userId)
//                mlocationClient!!.stopLocation()
            } else {
                val errText = "定位失败," + p0.getErrorCode() + ": " + p0.getErrorInfo()
                Log.e("AmapErr", errText)
//                mlocationClient!!.stopLocation()
            }
        }
    }

    //------------------地图相关方法
    override fun onCameraChangeFinish(p0: CameraPosition?) {

    }

    override fun onCameraChange(p0: CameraPosition?) {
    }

    override fun deactivate() {

    }

    private var mLocationOption: AMapLocationClientOption? = null
    private var mListener: LocationSource.OnLocationChangedListener? = null
    private var mlocationClient: AMapLocationClient? = null
    override fun activate(p0: LocationSource.OnLocationChangedListener?) {
        if (mListener == null) {
            mListener = p0
        }
        if (mlocationClient == null) {
            mlocationClient = AMapLocationClient(this)
            mLocationOption = AMapLocationClientOption()
            //设置定位监听
            mlocationClient!!.setLocationListener(this)
            //设置为高精度定位模式
            mLocationOption!!.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy)
            //设置定位参数
            mlocationClient!!.setLocationOption(mLocationOption)
            // 此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
            // 注意设置合适的定位时间的间隔（最小间隔支持为2000ms），并且在合适时间调用stopLocation()方法来取消定位请求
            // 在定位结束后，在合适的生命周期调用onDestroy()方法
            // 在单次定位情况下，定位无论成功与否，都无需调用stopLocation()方法移除请求，定位sdk内部会移除
            mlocationClient!!.startLocation()
        }
    }

    override fun initlayout(): Int {
        return R.layout.activity_withassenger
    }

    private var mUiSettings: UiSettings? = null
    var orderid = ""
    var userId = ""
    override fun initView() {
        userId = intent.getStringExtra("userId")
        orderid = intent.getStringExtra("orderid")
        withassenger_with.setOnClickListener {
        }
        withassenger_fache.setOnClickListener {
            mPersenter!!.fache(orderid, "2001")
        }
        bundle = Bundle()
        withassenger_map.onCreate(bundle)
//        初始化地图控制器对象
        if (aMap == null) {
            aMap = withassenger_map.getMap()
        }
        aMap = withassenger_map.map
        aMap!!.setMapLanguage(AMap.CHINESE)
        aMap!!.mapType = AMap.MAP_TYPE_NORMAL// 矢量地图模式
        aMap!!.setLocationSource(this)
        mUiSettings = aMap!!.uiSettings
        mUiSettings!!.isZoomControlsEnabled = false
//        mUiSettings!!.zoomPosition = 16
        // 是否显示定位按钮
        mUiSettings!!.isMyLocationButtonEnabled = false
        val myLocationStyle = MyLocationStyle()
        myLocationStyle.myLocationIcon(
            BitmapDescriptorFactory
                .fromResource(R.drawable.gps_point)
        )// 设置小蓝点的图标
        myLocationStyle.strokeColor(Color.TRANSPARENT)// 设置圆形的边框颜色
        myLocationStyle.radiusFillColor(Color.TRANSPARENT)// 设置圆形的填充颜色
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_FOLLOW)

        aMap!!.moveCamera(CameraUpdateFactory.zoomTo(17f))
        aMap!!.setMyLocationStyle(myLocationStyle)
        aMap!!.isMyLocationEnabled = true
        aMap!!.uiSettings.isRotateGesturesEnabled = false
//        Log.d("activity", Utils.sHA1(this.applicationContext))
        aMap!!.setOnCameraChangeListener(this)
    }

    override fun initPersenter() {
        mPersenter = WithPassengerPersenter(this, this)
    }

    override fun showlogin() {
        mLoading!!.show()
    }

    override fun dissmisslogin() {
        mLoading!!.dismiss()
    }


    override fun onDestroy() {
        super.onDestroy()
        if (withassenger_map != null)
            withassenger_map.onDestroy()
        if (mlocationClient != null) {
            mlocationClient!!.onDestroy()
        }
    }

    override fun onPause() {
        super.onPause()
        if (withassenger_map != null)
            withassenger_map.onPause()
        if (mlocationClient != null) {
            mlocationClient!!.startLocation()
        }
    }

    override fun onResume() {
        super.onResume()
        Log.d("千米", "onresume")
        if (withassenger_map != null) {
            withassenger_map.onResume()
        }
        if (mlocationClient != null) {
            mlocationClient!!.startLocation()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        bundle = outState
        withassenger_map.onSaveInstanceState(bundle)
    }

}