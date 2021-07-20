package com.beautycenter.android.domain

import com.beautycenter.android.presentation.models.response.ServiceCategoryResponse
import io.reactivex.Observable

interface ServiceCategoryRepository {
    fun getServicesCategories(): Observable<ServiceCategoryResponse>
}