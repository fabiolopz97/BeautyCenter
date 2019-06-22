package com.beautycenter.android.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import com.beautycenter.android.R
import com.beautycenter.android.data.MethodsUtils
import com.beautycenter.android.data.retrofit.RetrofitAuth
import com.beautycenter.android.models.Auth
import com.beautycenter.android.models.response.AuthResponse
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private val retrofitAuth = RetrofitAuth()
    private val context = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        /**
         * use shared preference to save token and data
         */
        textViewRegistry.setOnClickListener {
            val intentRegistry = Intent(context, RegistryActivity::class.java)
            startActivity(intentRegistry)
        }

        buttonLogin.setOnClickListener {
            if(validateLogin()){
                getDataAndLogin()
            }
            //val intentNavigation = Intent(this, NavigationActivity::class.java)
            //startActivity(intentNavigation)
        }
    }

    /**
     * pending modify method
     */
    override fun onStart() {
        super.onStart()
        if(result_token != ""){
            changeActivity(result_token, "")
        }
    }

    private fun getDataAndLogin() {
        val customerService = retrofitAuth.getAuthService()

        val call = customerService.login(getDataUI())
        call.enqueue(object: Callback<AuthResponse> {
            override fun onResponse(call: Call<AuthResponse>, response: Response<AuthResponse>) {
                if (response.isSuccessful){
                    val token = response.body()?.access_token
                    result_token = token.toString()
                    val name = response.body()?.user?.name
                    if (token != null && name != null) {
                        //change message with use String
                        MethodsUtils.showMessage(context, "message successful.")
                        changeActivity(token, name)
                    }
                } else {
                    MethodsUtils.showMessage(
                        context,
                        "Error no successful ${response.errorBody()}"
                    )
                }
            }

            override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
                MethodsUtils.showMessage(context, "$call Error onFailure $t")
            }
        })
    }

    /**
     * This method verifies fields
     * @return Boolean
     */
    private fun validateLogin(): Boolean {
        val email = editTextEmail.text.toString()
        val password = editTextPassword.text.toString()
        var state = true
        if(email.isEmpty()){
            textInputEmail.error = getString(R.string.error_required_field_mgs)
            state = false
        } else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            textInputEmail.error = getString(R.string.error_email_mgs)
            state = false
        } else {
            textInputEmail.error =  null
        }
        if(password.isEmpty()){
            textInputPassword.error = getString(R.string.error_required_field_mgs)
            state = false
        } else {
            textInputPassword.error = null
        }
        return state
    }

    private fun changeActivity(token: String, name:String){
        val intentNavigation = Intent(context, NavigationActivity::class.java)
        intentNavigation.putExtra(TOKEN_KEY, token)
        intentNavigation.putExtra(NAME_KEY, name)
        startActivity(intentNavigation)
    }

    /**
     * This method return a Auth object
     * @return Auth
     */
    private fun getDataUI(): Auth {
        val email = editTextEmail.text.toString()
        val password = editTextPassword.text.toString()
        return Auth(email, password)
    }

    companion object {
        const val TOKEN_KEY = "access_token"
        const val NAME_KEY = "name"
        var result_token = ""
    }
}
