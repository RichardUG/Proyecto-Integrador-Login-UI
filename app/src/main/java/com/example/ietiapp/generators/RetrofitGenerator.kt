package com.example.ietiapp.generators

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

class RetrofitGenerator {
    private var retrofitInstance: Retrofit? = null

    fun getInstance(storage: Storage, url: String): Retrofit? {
        if (retrofitInstance == null) {
            retrofitInstance = createRetrofitInstance(storage, url)
        }
        return retrofitInstance
    }

    private fun createRetrofitInstance(storage: Storage, url:String): Retrofit? {
        val gson = GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create()
        val builder =
            Retrofit.Builder() //Toco quemar porque al momento de compilar no me aparece la variable en la clase buildConfig
                .baseUrl(url)
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
}