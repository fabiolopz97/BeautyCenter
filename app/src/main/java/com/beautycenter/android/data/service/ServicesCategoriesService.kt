package com.beautycenter.android.data.service

import com.beautycenter.android.models.ServiceCategory
import retrofit2.Call
import retrofit2.http.GET

interface ServicesCategoriesService {
    @GET("category/")
    fun getServicesCategories(): Call<List<ServiceCategory>>
}