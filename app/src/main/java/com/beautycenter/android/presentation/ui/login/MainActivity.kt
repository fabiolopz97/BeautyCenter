package com.beautycenter.android.presentation.ui.login

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.beautycenter.android.R
import com.beautycenter.android.data.MethodsUtils
import com.beautycenter.android.presentation.models.Auth
import com.beautycenter.android.presentation.ui.mainnavigation.NavigationActivity
import com.beautycenter.android.presentation.ui.registry.RegistryActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        loginViewModel.mLiveData.observe(this, Observer {
            val token = it.access_token
            val name = it.user.name?:""
            if (name != "") {
                //change message with use String
                MethodsUtils.showMessage(this, "message successful.")
                changeActivity(token, name)
            }
        })

        /**
         * use shared preference to save token and data
         */
        textViewRegistry.setOnClickListener {
            val intentRegistry = Intent(this, RegistryActivity::class.java)
            startActivity(intentRegistry)
        }

        buttonLogin.setOnClickListener {
            //if(validateLogin()){
              //  loginViewModel.loginWithLiveData(getDataUI())
            //}
            val intentNavigation = Intent(this, NavigationActivity::class.java)
            startActivity(intentNavigation)
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
        val intentNavigation = Intent(this, NavigationActivity::class.java)
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
