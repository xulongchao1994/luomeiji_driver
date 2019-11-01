package com.android.luomeiji_driver.network

import android.content.Context
import android.os.Build.HOST
import android.text.TextUtils
import com.android.luomeiji_driver.Base.LuomeijiDriverApplistion
import com.franmontiel.persistentcookiejar.PersistentCookieJar
import com.franmontiel.persistentcookiejar.cache.SetCookieCache
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import okhttp3.*
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.io.IOException
import java.util.concurrent.TimeUnit


class ApiFactify private constructor() {
    companion object {

        @JvmField
        val HOST = "http://47.92.209.238:6001/"
        internal var mService: Api? = null
        internal var mOkHttpClient: OkHttpClient? = null

        private val RETRY_COUNT = 0

        @JvmStatic
        fun getInstance(): Api? {
            if (mService == null) {
                createRetrofit()
            }
            return mService
        }

        private fun createRetrofit() {
            mOkHttpClient = getOkHttpClient()
            val retrofit = Retrofit.Builder()
                .client(mOkHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(HOST)
                .build()
            mService = retrofit.create(Api::class.java)
        }

        @JvmStatic
        fun getOkHttpClient(): OkHttpClient {
            val cache = Cache(
                File(LuomeijiDriverApplistion.appContext.getExternalCacheDir(), "HttpCache"),
                (1024 * 1024 * 24).toLong()
            )
            return OkHttpClient.Builder()
                .addInterceptor(object : Interceptor {
                    override fun intercept(chain: Interceptor.Chain): Response {
                        val original = chain.request()
                        val requestBuilder = original.newBuilder()
//                            .addHeader("Accept-Encoding", "gzip")
//                            .addHeader("Accept", "application/json")
//                            .addHeader("Content-Type", "application/json; charset=utf-8")
//                            .method(original.method(), original.body());
                        if (original.body() is FormBody) {
                            val newFormBody = FormBody.Builder()
                            val oldFormBody = original.body() as FormBody
                            for (i in 0 until oldFormBody.size()) {
                                newFormBody.addEncoded(
                                    oldFormBody.encodedName(i),
                                    oldFormBody.encodedValue(i)
                                )
                            }
//                        newFormBody.add("fyToken", UserData.FYTOKENID)
                            requestBuilder.method(original.method(), newFormBody.build())
                        } else if (original.body() is MultipartBody) {
                            val newFormBody = MultipartBody.Builder()
                            // 默认是multipart/mixed，大坑【主要是我们php后台接收时头信息要求严格】
//                            newFormBody.setType(MediaType.parse("multipart/form-data"))
                            val oldFormBody = original.body() as MultipartBody
                            for (i in 0 until oldFormBody.size()) {
                                newFormBody.addPart(oldFormBody.part(i))
                            }
//                        newFormBody.addFormDataPart("fyToken", UserData.FYTOKENID)
                            requestBuilder.method(original.method(), newFormBody.build())
                        } else if (TextUtils.equals(original.method(), "POST")) {
                            val newFormBody = FormBody.Builder()
//                        newFormBody.add("fyToken", UserData.FYTOKENID)

                            requestBuilder.method(original.method(), newFormBody.build())
                        }

                        val request = requestBuilder.build()
                        return chain.proceed(request)
                    }
                })
                .readTimeout(300, TimeUnit.SECONDS)
                .connectTimeout(300, TimeUnit.SECONDS)
                .writeTimeout(300, TimeUnit.SECONDS)
                .followRedirects(false)
                .retryOnConnectionFailure(true)
//                .cache(cache)//开启缓存,还需要添加请求拦截器来配合,以达到网络缓存的作用
                .cookieJar(
                    PersistentCookieJar(
                        SetCookieCache(),
                        SharedPrefsCookiePersistor(LuomeijiDriverApplistion.appContext)
                    )
                )
                .build()
        }


        /**
         * 设置订阅 和 所在的线程环境
         */
        fun <T> toSubscribe(o: Observable<T>, s: DisposableObserver<T>) {
            o.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .retry(RETRY_COUNT.toLong())//请求失败重连次数
                .subscribe(s)

        }

        /**
         * 设置订阅 和 所在的线程环境
         */
        fun <T> fromSubscribe(o: Observable<T>, s: Observer<T>) {
            o.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .retry(RETRY_COUNT.toLong())//请求失败重连次数
                .subscribe(s)

        }
    }
}