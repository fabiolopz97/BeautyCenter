package com.beautycenter.android.domain

import com.beautycenter.android.presentation.models.response.ServiceResponse
import io.reactivex.Observable

interface ServiceRepository {
    fun getServices(): Observable<ServiceResponse>
    fun getServicesByCategory(serviceCategoryId:Int): Observable<ServiceResponse>
}