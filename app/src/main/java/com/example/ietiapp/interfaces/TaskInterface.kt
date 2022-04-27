package com.example.ietiapp.interfaces

import com.example.ietiapp.data.TasksDto
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*


interface TaskInterface {
    @GET
    fun getAll(): Call<List<TasksDto?>?>?

    @GET("/{id}")
    fun findById(@Path("id") id:String?):TasksDto

    @POST
    fun create(@Body requestBody: RequestBody):Response<ResponseBody>

    @PUT("/{id}")
    fun update(@Body requestBody: RequestBody, @Path("id") id:String):Response<ResponseBody>

    @DELETE("/id")
    fun delete(@Path("id") id: String):Response<ResponseBody>
}