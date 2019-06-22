package com.beautycenter.android.data.retrofit

import com.beautycenter.android.data.service.CustomersService
import com.beautycenter.android.data.PathUtils
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitCustomer {
    private val customer = Retrofit.Builder()
        .baseUrl(PathUtils.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getCustomersService(): CustomersService {
        return  customer.create(CustomersService::class.java)
    }
}