package com.beautycenter.android.presentation.ui.servicedetail

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.beautycenter.android.data.ServiceRepositoryImpl
import com.beautycenter.android.presentation.models.Service
import com.beautycenter.android.presentation.models.response.ServiceResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ServiceDetailViewModel: ViewModel() {
    private val serviceRepositoryImp = ServiceRepositoryImpl()
    var mLiveData = MutableLiveData<ServiceResponse>()

    fun getServiceDetailsWithLiveData(serviceCategoryId: Int) {
        serviceRepositoryImp.getServicesByCategory(serviceCategoryId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                mLiveData.value = it
                Log.i("SUCCESSFUL", "messageSuccessful $it")
            },{
                Log.i("ERROR_REGISTRY", "messageError $it")
            })
    }

}