package com.beautycenter.android.presentation.ui.servicedetail

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.beautycenter.android.R
import com.beautycenter.android.presentation.ui.mainnavigation.fragment.ServicesFragment
import com.beautycenter.android.presentation.models.Service
import kotlinx.android.synthetic.main.activity_service_detail.*

class ServiceDetailActivity : AppCompatActivity() {
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service_detail)
        showActionBar(intent.getStringExtra(ServicesFragment.NAME_ACTIVITY))

        val serviceDetailViewModel = ViewModelProviders.of(this).get(ServiceDetailViewModel::class.java)
        serviceDetailViewModel.mLiveData.observe(this, Observer {
            val services = it.data
            showServices(services)
        })
        val id = intent.getIntExtra(ServicesFragment.SERVICE_CATEGORY_ID_KEY, 0)
        serviceDetailViewModel.getServiceDetailsWithLiveData(id)

    }

    private fun showServices(serviceResponses: List<Service>) {
        val list = findViewById<RecyclerView>(R.id.listServicesDetail)
        viewManager = LinearLayoutManager(this)
        viewAdapter =
            ServiceAdapterRecyclerView(
                serviceResponses,
                R.layout.item_service_detail
            ) { service ->
                Toast.makeText(this, "Service clicked: ${service.name}", Toast.LENGTH_LONG).show()
            }

        list.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }

    private fun showActionBar(nameActivity: String?){
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = nameActivity
    }

}
