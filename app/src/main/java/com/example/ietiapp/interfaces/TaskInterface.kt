package com.example.ietiapp.interfaces

import com.example.ietiapp.data.TasksDto
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*


interface TaskInterface : AuthInterface {
    @GET("tasks")
    fun getAll(): rx.Observable<List<TasksDto>>

    @GET("tasks/{id}")
    fun findById(@Path("id") id:String?): rx.Observable<TasksDto>

    @POST
    fun create(@Body requestBody: RequestBody): rx.Observable<Object>

    @PUT("/{id}")
    fun update(@Body requestBody: RequestBody, @Path("id") id:String): rx.Observable<Object>

    @DELETE("/id")
    fun delete(@Path("id") id: String): rx.Observable<Object>

}