package com.example.ietiapp.interceptor

import com.example.ietiapp.storage.Storage
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class AuthInterceptor(storage: Storage) : Interceptor {
    private val storage: Storage = storage

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request.Builder = chain.request().newBuilder()
        val token: String = storage.getToken()
        if (!token.isEmpty()) {
            request.addHeader("Authorization", "Bearer $token")
        }
        return chain.proceed(request.build())
    }

}