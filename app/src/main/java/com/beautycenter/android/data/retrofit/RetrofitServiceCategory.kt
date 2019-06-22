package com.beautycenter.android.data.retrofit

import com.beautycenter.android.data.PathUtils
import com.beautycenter.android.data.service.ServicesCategoriesService
import com.beautycenter.android.data.service.ServicesService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitServiceCategory {
    private val serviceCategory = Retrofit.Builder()
        .baseUrl(PathUtils.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getServicesCategoriesService(): ServicesCategoriesService {
        return serviceCategory.create(ServicesCategoriesService::class.java)
    }
}