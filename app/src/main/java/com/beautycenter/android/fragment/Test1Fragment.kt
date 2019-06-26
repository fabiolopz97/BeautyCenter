package com.beautycenter.android.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.beautycenter.android.R
import com.beautycenter.android.adapters.ServiceCategoryAdapterRecyclerView
import com.beautycenter.android.data.MethodsUtils
import com.beautycenter.android.models.ServiceCategory


class Test1Fragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_test1, container, false)
        showServices(v, getServiceCategory())
        return v
    }

    private fun showServices(v:View, services: List<ServiceCategory>) {
        val list = v.findViewById<RecyclerView>(R.id.listPost)
        viewManager = LinearLayoutManager(v.context)
        viewAdapter = ServiceCategoryAdapterRecyclerView(services, R.layout.item_post){ service->
            MethodsUtils.showMessage(v.context, "Service clicked: ${service.name}")
        }

        recyclerView = list.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }

    /**
     * Pending delete this method, only test
     */
    private fun getServiceCategory() = listOf(
            ServiceCategory(1, "Publicación 1", ""+R.mipmap.logotype),
            ServiceCategory(2, "Publicación 2", ""+R.mipmap.logotype),
            ServiceCategory(3, "Publicación 3", ""+R.mipmap.logotype),
            ServiceCategory(4, "Publicación 4", ""+R.mipmap.logotype)
        )


}
