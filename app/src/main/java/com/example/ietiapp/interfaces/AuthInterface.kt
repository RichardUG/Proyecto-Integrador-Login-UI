package com.example.ietiapp.interfaces

import android.database.Observable
import com.example.ietiapp.data.LoginDto
import com.example.ietiapp.data.TokenDto
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.POST
import java.util.*

interface AuthInterface {
    @POST("auth")
    fun login(@Body requestBody: LoginDto): rx.Observable<TokenDto>
}