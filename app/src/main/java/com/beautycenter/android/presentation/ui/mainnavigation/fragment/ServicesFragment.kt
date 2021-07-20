package com.beautycenter.android.presentation.ui.mainnavigation.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.beautycenter.android.R
import com.beautycenter.android.presentation.ui.servicedetail.ServiceDetailActivity
import com.beautycenter.android.data.MethodsUtils
import com.beautycenter.android.presentation.models.ServiceCategory

class ServicesFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_services, container, false)
        val serviceViewModel = ViewModelProviders.of(this).get(ServiceViewModel::class.java)

        serviceViewModel.mLiveData.observe(this, Observer {
            val services = it.data
            showServices(v, services)
        })

        serviceViewModel.getServiceCategoriesWithLiveData()
        //getAndShowServices(v)
        return v
    }

    private fun showServices(v:View, services: List<ServiceCategory>) {
        val list = v.findViewById<RecyclerView>(R.id.listServices)
        viewManager = GridLayoutManager(v.context, 2)
        viewAdapter =
            ServiceCategoryAdapterRecyclerView(
                services,
                R.layout.item_service
            ) { service ->
                val intentServiceDetail = Intent(v.context, ServiceDetailActivity::class.java)
                //MethodsUtils.showMessage(v.context, "Service clicked: ${service.name}")
                intentServiceDetail.putExtra(SERVICE_CATEGORY_ID_KEY, service.id)
                intentServiceDetail.putExtra(NAME_ACTIVITY, service.name)
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
        const val NAME_ACTIVITY = "name_activity"
    }

}
