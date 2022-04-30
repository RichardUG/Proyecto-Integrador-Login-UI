package com.example.ietiapp.generators

import android.os.Build
import androidx.core.content.FileProvider
import com.example.ietiapp.BuildConfig
import com.example.ietiapp.R
import com.example.ietiapp.interceptor.AuthInterceptor
import com.example.ietiapp.storage.Storage
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import rx.schedulers.Schedulers
import kotlin.coroutines.coroutineContext

class RetrofitGenerator {
    private var retrofitInstance: Retrofit? = null

    fun getInstanceUsers(storage: Storage): Retrofit? {
        if (retrofitInstance == null) {
            retrofitInstance = createRetrofitInstanceUsers(storage)
        }
        return retrofitInstance
    }

    private fun createRetrofitInstanceUsers(storage: Storage): Retrofit? {
        val gson = GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create()
        val builder =
            Retrofit.Builder()
                .baseUrl(BuildConfig.APIUSERS)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(
                    RxJavaCallAdapterFactory
                        .createWithScheduler(Schedulers.io())
                )
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val okHttpClient: OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(AuthInterceptor(storage))
            .writeTimeout(0, TimeUnit.MILLISECONDS)
            .readTimeout(2, TimeUnit.MINUTES)
            .connectTimeout(1, TimeUnit.MINUTES)
            .build()
        return builder.client(okHttpClient).build()
    }

    fun getInstanceTasks(): Retrofit? {
        if (retrofitInstance == null) {
            retrofitInstance = createRetrofitInstanceTasks()
        }
        return retrofitInstance
    }

    private fun createRetrofitInstanceTasks(): Retrofit? {
        val gson = GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create()
        val builder =
            Retrofit.Builder()
                .baseUrl(BuildConfig.APITASKS)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(
                    RxJavaCallAdapterFactory
                        .createWithScheduler(Schedulers.io())
                )
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val okHttpClient: OkHttpClient = OkHttpClient.Builder()
            .writeTimeout(0, TimeUnit.MILLISECONDS)
            .readTimeout(2, TimeUnit.MINUTES)
            .connectTimeout(1, TimeUnit.MINUTES)
            .build()
        return builder.client(okHttpClient).build()
    }
}