package com.beautycenter.android.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.beautycenter.android.R
import com.beautycenter.android.data.MethodsUtils
import com.beautycenter.android.data.retrofit.RetrofitAuth
import com.beautycenter.android.models.response.AuthResponse
import kotlinx.android.synthetic.main.activity_test_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TestLoginActivity : AppCompatActivity() {
    private val retrofitAuth = RetrofitAuth()
    private val context = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_login)
        //me()
        buttonSignOut.setOnClickListener {
            logout()
        }
    }

    private fun logout() {
        val authService = retrofitAuth.getAuthService()
        val token = intent.getStringExtra(MainActivity.TOKEN_KEY)
            val name = intent.getStringExtra(MainActivity.NAME_KEY)
        Log.i("mensaje", "nombre = $name")
        val call = authService.logout(token)
        call.enqueue(object: Callback<AuthResponse> {
            override fun onResponse(call: Call<AuthResponse>, response: Response<AuthResponse>) {
                if (response.isSuccessful){
                    //change message with use String
                    MethodsUtils.showMessage(context, "message successful. ${response.body()?.message}")
                    Log.i("mensaje", "message successful. ${response.body()?.message}")

                } else {
                    MethodsUtils.showMessage(context, "Error no successful ${response.errorBody()}")
                }
            }

            override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
                MethodsUtils.showMessage(context, "$call Error onFailure $t")
            }
        })
    }

    /*private fun me() {
        val authService = retrofitAuth.getAuthService()
        val token = intent.getStringExtra(TOKEN_KEY)
        val call = authService.me(token)
        call.enqueue(object: Callback<Customer> {
            override fun onResponse(call: Call<Customer>, response: Response<Customer>) {
                if (response.isSuccessful){
                    //change message with use String
                    MethodsUtils.showMessage(context,"message successful. ${response.body()?.email}")
                    Log.i("mensaje", "message successful. ${response.body()?.email}")
                } else {
                    MethodsUtils.showMessage(context,"Error no successful ${response.errorBody()}")
                }
            }

            override fun onFailure(call: Call<Customer>, t: Throwable) {
                MethodsUtils.showMessage(context,"$call Error onFailure $t")
            }
        })
    }*/




}
