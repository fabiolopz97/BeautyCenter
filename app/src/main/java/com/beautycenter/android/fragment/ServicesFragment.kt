package com.beautycenter.android.fragment

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.beautycenter.android.R
import com.beautycenter.android.activity.ServiceDetailActivity
import com.beautycenter.android.adapters.ServiceCategoryAdapterRecyclerView
import com.beautycenter.android.data.MethodsUtils
import com.beautycenter.android.data.retrofit.RetrofitServiceCategory
import com.beautycenter.android.models.ServiceCategory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ServicesFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    private val retrofitService = RetrofitServiceCategory()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_services, container, false)
        getAndShowServices(v)
        return v
    }

    private fun getAndShowServices(v:View) {
        val servicesService = retrofitService.getServicesCategoriesService()
        val call = servicesService.getServicesCategories()
        call.enqueue(object: Callback<List<ServiceCategory>> {

            override fun onFailure(call: Call<List<ServiceCategory>>, t: Throwable) {
                MethodsUtils.showMessage(v.context, "$call Error onFailure $t")
                Log.i("mensaje de error", "$call Error onFailure $t")
            }

            override fun onResponse(call: Call<List<ServiceCategory>>, response: Response<List<ServiceCategory>>) {
                if (response.isSuccessful){
                    val services = response.body()?: emptyList()
                    showServices(v, services)
                } else {
                    MethodsUtils.showMessage(v.context, "Error no successful ${response.errorBody()}")
                }
            }
        })
    }

    private fun showServices(v:View, services: List<ServiceCategory>) {
        val list = v.findViewById<RecyclerView>(R.id.listServices)
        viewManager = GridLayoutManager(v.context, 2)
        viewAdapter = ServiceCategoryAdapterRecyclerView(services, R.layout.item_service){ service->
            val intentServiceDetail = Intent(v.context, ServiceDetailActivity::class.java)
            MethodsUtils.showMessage(v.context, "Service clicked: ${service.name}")
            intentServiceDetail.putExtra(SERVICE_CATEGORY_ID_KEY, service.id)
            v.context.startActivity(intentServiceDetail)
        }

        recyclerView = list.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }

    companion object {
        const val SERVICE_CATEGORY_ID_KEY = "service_category_id"
    }

}
