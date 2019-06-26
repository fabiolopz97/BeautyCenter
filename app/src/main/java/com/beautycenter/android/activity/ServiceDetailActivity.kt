package com.beautycenter.android.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.util.Log
import android.widget.Toast
import com.beautycenter.android.R
import com.beautycenter.android.adapters.ServiceAdapterRecyclerView
import com.beautycenter.android.data.MethodsUtils
import com.beautycenter.android.data.retrofit.RetrofitService
import com.beautycenter.android.fragment.ServicesFragment
import com.beautycenter.android.models.Service
import kotlinx.android.synthetic.main.activity_registry.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ServiceDetailActivity : AppCompatActivity() {
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    private val retrofitService = RetrofitService()
    private val context = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        showActionBar(intent.getStringExtra(ServicesFragment.NAME_ACTIVITY))
        getAndShowServices()
    }

    private fun getAndShowServices() {
        val servicesService = retrofitService.getServicesService()
        val id = intent.getIntExtra(ServicesFragment.SERVICE_CATEGORY_ID_KEY, 0)
        val call = servicesService.getServicesByCategory(id)
        call.enqueue(object: Callback<List<Service>> {

            override fun onFailure(call: Call<List<Service>>, t: Throwable) {
                MethodsUtils.showMessage(context, "$call Error onFailure $t")
                Log.i("mensaje de error", "$call Error onFailure $t")
            }

            override fun onResponse(call: Call<List<Service>>, response: Response<List<Service>>) {
                if (response.isSuccessful){
                    val services = response.body()?: emptyList()
                    showServices(services)
                } else {
                    MethodsUtils.showMessage(
                        context,
                        "Error no successful ${response.errorBody()}"
                    )
                }
            }
        })
    }

    private fun showServices(services: List<Service>) {
        val list = findViewById<RecyclerView>(R.id.listServicesDetail)
        viewManager = LinearLayoutManager(this)
        viewAdapter = ServiceAdapterRecyclerView(services, R.layout.item_service_detail){ service->
            Toast.makeText(this,"Service clicked: ${service.name}", Toast.LENGTH_LONG).show()
        }

        list.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }

    private fun showActionBar(nameActivity: String){
        setContentView(R.layout.activity_service_detail)
        setSupportActionBar(toolbar as Toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = nameActivity
    }

}
