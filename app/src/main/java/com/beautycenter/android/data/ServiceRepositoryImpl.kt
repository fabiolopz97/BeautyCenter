package com.beautycenter.android.data

import com.beautycenter.android.data.network.RetrofitClient
import com.beautycenter.android.data.network.ServiceApi
import com.beautycenter.android.domain.ServiceRepository
import com.beautycenter.android.presentation.models.response.ServiceResponse
import io.reactivex.Observable

class ServiceRepositoryImpl: ServiceRepository {

    private val serviceApi = RetrofitClient()
        .getRetrofitClient()
        .create(ServiceApi::class.java)

    override fun getServices(): Observable<ServiceResponse> {
        return serviceApi.getServices().map { it }
    }

    override fun getServicesByCategory(serviceCategoryId: Int): Observable<ServiceResponse> {
        return serviceApi.getServicesByCategory(serviceCategoryId).map { it }
    }

}