package com.beautycenter.android.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.util.Patterns
import com.beautycenter.android.R
import com.beautycenter.android.data.MethodsUtils
import com.beautycenter.android.data.retrofit.RetrofitCustomer
import com.beautycenter.android.models.Customer
import kotlinx.android.synthetic.main.activity_registry.*
import kotlinx.android.synthetic.main.activity_service_detail.toolbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RegistryActivity : AppCompatActivity() {
    private val retrofitCustomer = RetrofitCustomer()
    private val context = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registry)
        showActionBar()
        // User registry
        buttonRegistry.setOnClickListener {
            if(validateSingUp()){
                getAndStoreCustomer()
            }
        }
    }

    /**
     * Own methods
     */
    private fun getAndStoreCustomer() {
        val customerService = retrofitCustomer.getCustomersService()
        val call = customerService.storeCustomer(getDataUI())
        call.enqueue(object: Callback<Customer> {

            override fun onResponse(call: Call<Customer>, response: Response<Customer>) {
                if (response.isSuccessful){
                    cleanUI()
                    //change message with use String
                    MethodsUtils.showMessage(context, "message successful.")
                } else {
                    MethodsUtils.showMessage(
                        context,
                        "Error no successful ${response.errorBody()}"
                    )
                }
            }

            override fun onFailure(call: Call<Customer>, t: Throwable) {
                MethodsUtils.showMessage(context, "$call Error onFailure $t")
            }
        })
    }

    private fun getDataUI():Customer {
        val name = editTextName.text.toString()
        val lastName = editTextLastName.text.toString()
        val birthday = editTextBirthday.text.toString()
        val phone = editTextPhone.text.toString()
        val email = editTextEmail.text.toString()
        val password = editTextPassword.text.toString()
        //this is the format for date -> "2019-02-02"
        return Customer(null, name, lastName, birthday, phone, email, password)
    }

    private fun cleanUI(){
        editTextName.setText("")
        editTextLastName.setText("")
        editTextBirthday.setText("")
        editTextPhone.setText("")
        editTextEmail.setText("")
        editTextPassword.setText("")
    }

    /**
     * This method verifies fields
     * @return Boolean
     */
    private fun validateSingUp(): Boolean {
        var state = true
        val name = editTextName.text.toString()
        val lastName = editTextLastName.text.toString()
        val birthday = editTextBirthday.text.toString()
        val phone = editTextPhone.text.toString()
        val email = editTextEmail.text.toString()
        val password = editTextPassword.text.toString()

        if(name.isEmpty()){
            textInputName.error = getString(R.string.error_required_field_mgs)
            state = false
        } else {
            textInputName.error =  null
        }
        if(lastName.isEmpty()){
            textInputLastName.error = getString(R.string.error_required_field_mgs)
            state = false
        } else {
            textInputLastName.error =  null
        }
        if(birthday.isEmpty()){
            textInputBirthday.error = getString(R.string.error_required_field_mgs)
            state = false
        } else {
            textInputBirthday.error =  null
        }
        if(phone.isEmpty()){
            textInputPhone.error = getString(R.string.error_required_field_mgs)
            state = false
        } else {
            textInputPhone.error =  null
        }
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

    private fun showActionBar(){
        setSupportActionBar(toolbar as Toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    /*override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }*/
}
