package com.beautycenter.android.presentation.ui.registry

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.beautycenter.android.data.CustomerRepositoryImpl
import com.beautycenter.android.presentation.models.Auth
import com.beautycenter.android.presentation.models.Customer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class RegistryViewModel: ViewModel() {
    private val customerRepository = CustomerRepositoryImpl()

    var mLiveData = MutableLiveData<Customer>()

    fun registryWithLiveData(customer: Customer) {
        customerRepository.storeCustomer(customer)
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