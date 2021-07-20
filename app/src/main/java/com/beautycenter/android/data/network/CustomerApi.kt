package com.beautycenter.android.data.network

import com.beautycenter.android.presentation.models.Customer
import com.beautycenter.android.presentation.models.response.CustomerResponse
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface CustomerApi {
    @Headers(
        "Content-Type: application/json;charset=utf-8",
        "Accept: application/json;charset=utf-8",
        "Cache-Control: max-age=640000"
    )
    @POST("customer/")
    fun storeCustomer(@Body customer: Customer): Observable<Customer>

    @GET("customer/")
    fun getCustomers(): Observable<CustomerResponse>
}