package com.beautycenter.android.domain

import com.beautycenter.android.presentation.models.Customer
import com.beautycenter.android.presentation.models.response.CustomerResponse
import io.reactivex.Observable

interface CustomerRepository {
    fun storeCustomer(customer: Customer): Observable<Customer>
    fun getCustomers(): Observable<CustomerResponse>
}