package com.beautycenter.android.data.network

import com.beautycenter.android.presentation.models.response.ServiceResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface ServiceApi {
    @GET("service/")
    fun getServices(): Observable<ServiceResponse>

    @GET("service/category/{id}")
    fun getServicesByCategory(@Path("id") serviceCategoryId:Int): Observable<ServiceResponse>
}