package com.beautycenter.android.presentation.ui.mainnavigation.fragment

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.beautycenter.android.data.ServiceCategoryRepositoryImpl
import com.beautycenter.android.presentation.models.response.ServiceCategoryResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ServiceViewModel: ViewModel() {
    private val serviceRepositoryImp = ServiceCategoryRepositoryImpl()
    var mLiveData = MutableLiveData<ServiceCategoryResponse>()

    fun getServiceCategoriesWithLiveData() {
        serviceRepositoryImp.getServicesCategories()
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