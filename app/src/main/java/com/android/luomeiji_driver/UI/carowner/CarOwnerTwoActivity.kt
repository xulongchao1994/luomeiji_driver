package com.android.luomeiji_driver.UI.carowner

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.service.autofill.UserData
import android.util.Log
import android.widget.ImageView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.android.luomeiji_driver.Base.LBaseAppCompatActivity
import com.android.luomeiji_driver.R
import com.android.luomeiji_driver.Tools.MyToast
import com.android.luomeiji_driver.UI.legalize.CarLegalizeActivity
import com.android.luomeiji_driver.network.GetCodeBean
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import com.vondear.rxtool.RxPhotoTool
import com.vondear.rxtool.RxSPTool
import com.vondear.rxui.view.dialog.RxDialogChooseImage
import com.yalantis.ucrop.UCrop
import com.yalantis.ucrop.UCropActivity
import kotlinx.android.synthetic.main.activity_carowner_two.*
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class CarOwnerTwoActivity : LBaseAppCompatActivity<CarOwnerTwoPersenter>(), ICarOwnerTwoView {
    override fun carownertwosuccess(getCodeBean: GetCodeBean) {
        startActivity(Intent(this, CarLegalizeActivity::class.java))
    }

    override fun carownertwoerror(string: String) {
        MyToast.showMsg(string)
    }

    override fun initlayout(): Int {
        return R.layout.activity_carowner_two
    }

    var imagenumber = 0
    var carname = ""
    var caridcar = ""
    var carid = ""
    var sp: SharedPreferences? = null
    override fun initView() {
        sp = getSharedPreferences("USERINFO", Context.MODE_PRIVATE)
        carname = intent.getStringExtra("name")
        caridcar = intent.getStringExtra("idcar")
        carid = sp!!.getString("driveruserid", "").toString()
        carowner_two_id_zheng.setOnClickListener {
            openphoto(1)
        }
        carowner_two_id_fan.setOnClickListener {
            openphoto(2)
        }
        carowner_two_man_zheng.setOnClickListener {
            openphoto(3)
        }
        carowner_two_car_zheng.setOnClickListener {
            openphoto(4)
        }
        carowner_two_next.setOnClickListener {
            Log.d("图片地址1", phonefile1!!.path)
            Log.d("图片地址2", phonefile2!!.path)
            Log.d("图片地址3", phonefile3!!.path)
            Log.d("图片地址4", phonefile4!!.path)
            if (phonefile1 == null) {
                MyToast.showMsg("请选择身份证正面照片")
                return@setOnClickListener
            }
            if (phonefile2 == null) {
                MyToast.showMsg("请选择身份证反面照片")
                return@setOnClickListener
            }
            if (phonefile3 == null) {
                MyToast.showMsg("请选择手持身份证照片")
                return@setOnClickListener
            }
            if (phonefile4 == null) {
                MyToast.showMsg("请选择驾驶证照片")
                return@setOnClickListener
            }
            mPersenter!!.carownertwo(phonefile1!!, phonefile2!!, phonefile3!!, phonefile4!!, carid)
        }
    }

    private fun openphoto(imgnumber: Int) {
        if (Build.VERSION.SDK_INT >= 23) {
            val checkCallPhonePermission = ContextCompat.checkSelfPermission(
                this, Manifest.permission.CAMERA
            )
            if (checkCallPhonePermission != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(
                    arrayOf(
                        Manifest.permission.CAMERA
                    ), 111
                )
            } else {
                imagenumber = imgnumber
//                    val intent = Intent()
//                    intent.type = "image/*"
//                    intent.action = Intent.ACTION_GET_CONTENT
//                    startActivityForResult(intent, 1111)

                RxPhotoTool.openLocalImage(this)
//                    val dialogChooseImage =
//                        RxDialogChooseImage(this, RxDialogChooseImage.LayoutType.TITLE)
//                    dialogChooseImage.show()
            }
        } else {
            imagenumber = imgnumber
//                val intent = Intent()
//                intent.type = "image/*"
//                intent.action = Intent.ACTION_GET_CONTENT
//                startActivityForResult(intent, 1111)

            RxPhotoTool.openLocalImage(this)
//                val dialogChooseImage =
//                    RxDialogChooseImage(this, RxDialogChooseImage.LayoutType.TITLE)
//                dialogChooseImage.show()
        }
    }

    var resultUri1: Uri? = null
    var phonefile1: File? = null

    var resultUri2: Uri? = null
    var phonefile2: File? = null


    var resultUri3: Uri? = null
    var phonefile3: File? = null

    var resultUri4: Uri? = null
    var phonefile4: File? = null

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (requestCode) {
            RxPhotoTool.GET_IMAGE_FROM_PHONE//选择相册之后的处理
            -> if (resultCode == Activity.RESULT_OK) {
                //                    RxPhotoTool.cropImage(ActivityUser.this, );// 裁剪图片
                initUCrop(data!!.data)
            }
            RxPhotoTool.GET_IMAGE_BY_CAMERA//选择照相机之后的处理
            -> if (resultCode == Activity.RESULT_OK) {
                /* data.getExtras().get("data");*/
                //                    RxPhotoTool.cropImage(ActivityUser.this, RxPhotoTool.imageUriFromCamera);// 裁剪图片
                initUCrop(RxPhotoTool.imageUriFromCamera)
            }
            RxPhotoTool.CROP_IMAGE//普通裁剪后的处理
            -> {
                val options = RequestOptions()
                    .placeholder(R.mipmap.ic_launcher)
                    //异常占位图(当加载异常的时候出现的图片)
                    .error(R.mipmap.ic_launcher)
                    //禁止Glide硬盘缓存缓存
                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                when (imagenumber) {
                    1 -> {
                        Glide.with(this).load(RxPhotoTool.cropImageUri).apply(options).thumbnail(0.5f)
                            .into(carowner_two_id_zheng)
                        Log.d("图片地址1", RxPhotoTool.CROP_IMAGE.toString() + RxPhotoTool.cropImageUri.toString())
                    }
                    2 -> {
                        Glide.with(this).load(RxPhotoTool.cropImageUri).apply(options).thumbnail(0.5f)
                            .into(carowner_two_id_fan)
                        Log.d("图片地址2", RxPhotoTool.CROP_IMAGE.toString() + RxPhotoTool.cropImageUri.toString())
                    }
                    3 -> {
                        Glide.with(this).load(RxPhotoTool.cropImageUri).apply(options).thumbnail(0.5f)
                            .into(carowner_two_man_zheng)
                        Log.d("图片地址3", RxPhotoTool.CROP_IMAGE.toString() + RxPhotoTool.cropImageUri.toString())
                    }
                    4 -> {
                        Glide.with(this).load(RxPhotoTool.cropImageUri).apply(options).thumbnail(0.5f)
                            .into(carowner_two_car_zheng)
                        Log.d("图片地址4", RxPhotoTool.CROP_IMAGE.toString() + RxPhotoTool.cropImageUri.toString())
                    }
                }
            }

            UCrop.REQUEST_CROP//UCrop裁剪之后的处理
            -> if (resultCode == Activity.RESULT_OK) {
                when (imagenumber) {
                    1 -> {
                        resultUri1 = UCrop.getOutput(data!!)
                        phonefile1 = roadImageView(resultUri1!!, carowner_two_id_zheng)
                        Log.d("图片地址1", UCrop.REQUEST_CROP.toString() + resultUri1.toString())
                        Log.d("图片地址1", UCrop.REQUEST_CROP.toString() + phonefile1.toString())
                    }
                    2 -> {
                        resultUri2 = UCrop.getOutput(data!!)
                        phonefile2 = roadImageView(resultUri2!!, carowner_two_id_fan)
                        Log.d("图片地址2", UCrop.REQUEST_CROP.toString() + resultUri2.toString())
                        Log.d("图片地址2", UCrop.REQUEST_CROP.toString() + phonefile2.toString())
                    }
                    3 -> {
                        resultUri3 = UCrop.getOutput(data!!)
                        phonefile3 = roadImageView(resultUri3!!, carowner_two_man_zheng)
                        Log.d("图片地址3", UCrop.REQUEST_CROP.toString() + resultUri3.toString())
                        Log.d("图片地址3", UCrop.REQUEST_CROP.toString() + phonefile3.toString())
                    }
                    4 -> {
                        resultUri4 = UCrop.getOutput(data!!)
                        phonefile4 = roadImageView(resultUri4!!, carowner_two_car_zheng)
                        Log.d("图片地址4", UCrop.REQUEST_CROP.toString() + resultUri4.toString())
                        Log.d("图片地址4", UCrop.REQUEST_CROP.toString() + phonefile4.toString())
                    }
                }
//                var file = File(resultUri.toString())
//                var requestFile = RequestBody.create(MediaType.parse("image/jpg"), file)
//                val body = MultipartBody.Part.createFormData("file", file.name, requestFile)
//                var jsong = JSONObject()
//                var jsonarr = JSONArray()
//                jsonarr.put(KUtils.bitmapToStringforfile(this, phonefile!!.path))
//                jsong.put("data", jsonarr)
//                images = jsong.toString()
//                RxSPTool.putContent(this, "AVATAR", phonefile.toString())
            } else if (resultCode == UCrop.RESULT_ERROR) {
                val cropError = UCrop.getError(data!!)
            }
            //UCrop裁剪错误之后的处理
            UCrop.RESULT_ERROR -> {
                val cropError = UCrop.getError(data!!)
            }
            else -> {
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    //从Uri中加载图片 并将其转化成File文件返回
    private fun roadImageView(uri: Uri, imageView: ImageView): File {
        val options = RequestOptions()
            .placeholder(R.mipmap.ic_launcher)
            //异常占位图(当加载异常的时候出现的图片)
            .error(R.mipmap.ic_launcher)
//            .transform(CircleCrop())
            //禁止Glide硬盘缓存缓存
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)

        Glide.with(this).load(uri).apply(options).thumbnail(0.5f).into(imageView)

        return File(RxPhotoTool.getImageAbsolutePath(this, uri))
    }

    private fun initUCrop(uri: Uri?) {
        val timeFormatter = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.CHINA)
        val time = System.currentTimeMillis()
        val imageName = timeFormatter.format(Date(time))

        val destinationUri = Uri.fromFile(File(cacheDir, "$imageName.jpeg"))

        val options = UCrop.Options()
        //设置裁剪图片可操作的手势
        options.setAllowedGestures(UCropActivity.SCALE, UCropActivity.ROTATE, UCropActivity.ALL)
        //设置隐藏底部容器，默认显示
        //options.setHideBottomControls(true);
        //设置toolbar颜色
        options.setToolbarColor(ActivityCompat.getColor(this, R.color.colorPrimary))
        //设置状态栏颜色
        options.setStatusBarColor(ActivityCompat.getColor(this, R.color.colorPrimaryDark))

        //开始设置
        //设置最大缩放比例
        options.setMaxScaleMultiplier(5f)
        //设置图片在切换比例时的动画f
        options.setImageToCropBoundsAnimDuration(666)
        //设置裁剪窗口是否为椭圆
        //options.setCircleDimmedLayer(true);
        //设置是否展示矩形裁剪框
        // options.setShowCropFrame(false);
        //设置裁剪框横竖线的宽度
        //options.setCropGridStrokeWidth(20);
        //设置裁剪框横竖线的颜色
        //options.setCropGridColor(Color.GREEN);
        //设置竖线的数量
        //options.setCropGridColumnCount(2);
        //设置横线的数量
        //options.setCropGridRowCount(1);
        Log.d("图片地址_图片剪裁", uri!!.path)
        UCrop.of(uri!!, destinationUri)
            .withAspectRatio(1f, 1f)
            .withMaxResultSize(1000, 1000)
            .withOptions(options)
            .start(this)

    }

    override fun initPersenter() {
        mPersenter = CarOwnerTwoPersenter(this, this)
    }

    override fun showlogin() {
    }

    override fun dissmisslogin() {
    }
}