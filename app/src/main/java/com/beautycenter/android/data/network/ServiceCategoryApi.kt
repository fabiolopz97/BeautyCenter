package com.beautycenter.android.data.network

import com.beautycenter.android.presentation.models.response.ServiceCategoryResponse
import io.reactivex.Observable
import retrofit2.http.GET

interface ServiceCategoryApi {
    @GET("category/")
    fun getServicesCategories(): Observable<ServiceCategoryResponse>
}