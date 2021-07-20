package com.beautycenter.android.presentation.ui.login

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.beautycenter.android.data.AuthRepositoryImpl
import com.beautycenter.android.presentation.models.Auth
import com.beautycenter.android.presentation.models.response.AuthResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class LoginViewModel: ViewModel() {
    private val loginRepository = AuthRepositoryImpl()

    var mLiveData = MutableLiveData<AuthResponse>()
    /*var mLiveDataTask = MutableLiveData<PendingTaskResponse>()
    var mLiveDataHistoryTask = MutableLiveData<PendingTaskResponse>()
    var mLiveDataCategoryConfig = MutableLiveData<CategoryConfigResponse>()*/

    fun loginWithLiveData(auth: Auth){
        loginRepository.login(auth)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                mLiveData.value = it
                Log.i("SUCCESSFUL", "messageSuccessful $it")
            },{
                Log.i("ERROR_LOGIN", "messageError $it")
            })
    }
}