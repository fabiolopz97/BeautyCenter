package com.beautycenter.android.data.service

import com.beautycenter.android.models.Service
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ServicesService {
    @GET("service/")
    fun getServices(): Call<List<Service>>

    @GET("service/category/{id}")
    fun getServicesByCategory(@Path("id") serviceCategoryId:Int): Call<List<Service>>
}