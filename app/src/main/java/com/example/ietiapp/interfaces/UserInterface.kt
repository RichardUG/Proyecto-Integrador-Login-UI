package com.example.ietiapp.interfaces

import com.example.ietiapp.data.TasksDto
import com.example.ietiapp.data.UserDto
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface UserInterface {
    @GET("user")
    fun getAll(): rx.Observable<List<UserDto>>

    @GET("user/{id}")
    fun findById(@Path("id") id:String?): rx.Observable<UserDto>

    @GET("user/findUsersWithNameOrLastNameLike/{queryparam}")
    fun findUsersWithNameOrLastNameLike(@Path("queryparam") queryparam:String?): rx.Observable<List<UserDto>>

    @GET("user/FindByDate/{date}")
    fun findUsersCreatedAfter(@Path("date") date:String?): rx.Observable<List<UserDto>>

    @POST("user")
    fun create(@Body requestBody: UserDto): rx.Observable<Object>

    @PUT("user/{id}")
    fun update(@Body requestBody: UserDto, @Path("id") id:String): rx.Observable<Object>

    @DELETE("user/id")
    fun delete(@Path("id") id: String): rx.Observable<Object>
}