package com.beautycenter.android.data

import com.beautycenter.android.data.network.CustomerApi
import com.beautycenter.android.data.network.RetrofitClient
import com.beautycenter.android.domain.CustomerRepository
import com.beautycenter.android.presentation.models.Customer
import com.beautycenter.android.presentation.models.response.CustomerResponse
import io.reactivex.Observable

class CustomerRepositoryImpl: CustomerRepository {

    private val customerApi = RetrofitClient()
        .getRetrofitClient()
        .create(CustomerApi::class.java)

    override fun storeCustomer(customer: Customer): Observable<Customer> {
        return customerApi.storeCustomer(customer).map { it }
    }

    override fun getCustomers(): Observable<CustomerResponse> {
        return customerApi.getCustomers().map { it }
    }

}