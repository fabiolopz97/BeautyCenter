package com.beautycenter.android.presentation.ui.registry

import android.os.Bundle
import android.util.Patterns
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.beautycenter.android.R
import com.beautycenter.android.data.MethodsUtils
import com.beautycenter.android.presentation.models.Customer
import kotlinx.android.synthetic.main.activity_registry.*

class RegistryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registry)
        showActionBar()

        val registryViewModel = ViewModelProvider(this).get(RegistryViewModel::class.java)

        registryViewModel.mLiveData.observe(this, Observer {
            cleanUI()
            //change message with use String
            MethodsUtils.showMessage(this@RegistryActivity, "message successful.")
        })

        // User registry
        buttonRegistry.setOnClickListener {
           if(validateSingUp()){
               registryViewModel.registryWithLiveData(getDataUI())
           }
        }
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
        supportActionBar?.show()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.title_action_bar)
    }

    /*override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }*/
}
