package com.beautycenter.android.data

import com.beautycenter.android.data.network.RetrofitClient
import com.beautycenter.android.data.network.ServiceCategoryApi
import com.beautycenter.android.domain.ServiceCategoryRepository
import com.beautycenter.android.presentation.models.ServiceCategory
import com.beautycenter.android.presentation.models.response.ServiceCategoryResponse
import io.reactivex.Observable

class ServiceCategoryRepositoryImpl: ServiceCategoryRepository {

    private val serviceCategoryApi = RetrofitClient()
        .getRetrofitClient()
        .create(ServiceCategoryApi::class.java)

    override fun getServicesCategories(): Observable<ServiceCategoryResponse> {
        return serviceCategoryApi.getServicesCategories().map { it }
    }

}