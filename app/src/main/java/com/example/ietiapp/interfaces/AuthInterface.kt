package com.example.ietiapp.interfaces

import com.example.ietiapp.data.TokenDto
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthInterface {
    @POST
    fun login(@Body requestBody: RequestBody): TokenDto
}