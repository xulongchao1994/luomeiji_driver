package com.android.luomeiji_driver.UI.main

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
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
import com.android.luomeiji_driver.Base.LBaseMapAppCompatActivity
import com.android.luomeiji_driver.R
import com.android.luomeiji_driver.UI.taxiorders.TaxiOrdersActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : LBaseMapAppCompatActivity<MainPersenter>(), IMainView, LocationSource,
    AMap.OnCameraChangeListener, AMapLocationListener {
    override fun onLocationChanged(p0: AMapLocation?) {
        if (mListener != null && p0 != null) {
            if (p0 != null && p0.getErrorCode() == 0) {
                mListener!!.onLocationChanged(p0)// 显示系统小蓝点
                aMap!!.moveCamera(
                    CameraUpdateFactory
                        .newLatLngZoom(LatLng(p0.latitude, p0.longitude), 17f)
                )// 设置指定的可视区域地图
                mlocationClient!!.stopLocation()
            } else {
                val errText = "定位失败," + p0.getErrorCode() + ": " + p0.getErrorInfo()
                Log.e("AmapErr", errText)
                mlocationClient!!.stopLocation()
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

    //*************************************************
    override fun initlayout(): Int {
        return R.layout.activity_main
    }

    var aMap: AMap? = null
    var bundle: Bundle? = null
    private var mUiSettings: UiSettings? = null

    override fun initView() {
        if (Build.VERSION.SDK_INT >= 23) {
            val checkCallPhonePermission = ContextCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_COARSE_LOCATION
            )
            if (checkCallPhonePermission != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(
                    arrayOf(
                        //定位需要的权限
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE
                    ), 123
                )
            } else {
            }
        } else {
        }
        bundle = Bundle()
        main_map.onCreate(bundle)
//        初始化地图控制器对象
        if (aMap == null) {
            aMap = main_map.getMap()
        }
        aMap = main_map.map
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
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_SHOW)

        aMap!!.moveCamera(CameraUpdateFactory.zoomTo(17f))
        aMap!!.setMyLocationStyle(myLocationStyle)
        aMap!!.isMyLocationEnabled = true
        aMap!!.uiSettings.isRotateGesturesEnabled = false
//        Log.d("activity", Utils.sHA1(this.applicationContext))
        aMap!!.setOnCameraChangeListener(this)
        main_taxi.setOnClickListener {
            startActivity(Intent(this, TaxiOrdersActivity::class.java))
        }
    }

    override fun initPersenter() {
        mPersenter = MainPersenter(this, this)
    }

    override fun showlogin() {
        mLoading!!.show()
    }

    override fun dissmisslogin() {
        mLoading!!.dismiss()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (main_map != null)
            main_map.onDestroy()
        if (mlocationClient != null) {
            mlocationClient!!.onDestroy()
        }
    }

    override fun onPause() {
        super.onPause()
        if (main_map != null)
            main_map.onPause()
        if (mlocationClient != null) {
            mlocationClient!!.startLocation()
        }
    }

    override fun onResume() {
        super.onResume()
        Log.d("千米", "onresume")
        if (main_map != null) {
            main_map.onResume()
        }
        if (mlocationClient != null) {
            mlocationClient!!.startLocation()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        bundle = outState
        main_map.onSaveInstanceState(bundle)
    }

}
