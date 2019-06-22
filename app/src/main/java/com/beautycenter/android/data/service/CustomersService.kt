package com.beautycenter.android.data.service

import com.beautycenter.android.models.Customer
import com.beautycenter.android.models.response.CustomerResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface CustomersService {
    @Headers(
        "Content-Type: application/json;charset=utf-8",
        "Accept: application/json;charset=utf-8",
        "Cache-Control: max-age=640000"
    )
    @POST("customer/")
    fun storeCustomer(@Body customer: Customer): Call<Customer>

    @GET("customer/")
    fun getCustomers(): Call<CustomerResponse>
}