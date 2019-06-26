package com.beautycenter.android.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.beautycenter.android.R
import com.beautycenter.android.adapters.AppointmentAdapterRecyclerView
import com.beautycenter.android.adapters.ServiceCategoryAdapterRecyclerView
import com.beautycenter.android.data.MethodsUtils
import com.beautycenter.android.data.retrofit.RetrofitServiceCategory
import com.beautycenter.android.models.Appointment
import com.beautycenter.android.models.ServiceCategory

class Test2Fragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_test2, container, false)
        showAppointment(v, getAppointment())
        return v
    }

    private fun showAppointment(v:View, appointment: List<Appointment>) {
        val list = v.findViewById<RecyclerView>(R.id.listAppointment)
        viewManager = LinearLayoutManager(v.context)
        viewAdapter = AppointmentAdapterRecyclerView(appointment, R.layout.item_appointment){ appointment->
            MethodsUtils.showMessage(v.context, "Appointment clicked: ${appointment.name}")
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
    private fun getAppointment() = listOf(
        Appointment("Nombre de la Cita pendiente", "Descripción", ""+R.mipmap.logotype),
        Appointment("Nombre de la Cita pendiente", "Descripción", ""+R.mipmap.logotype),
        Appointment("Nombre de la Cita pendiente", "Descripción", ""+R.mipmap.logotype),
        Appointment("Nombre de la Cita pendiente", "Descripción", ""+R.mipmap.logotype)
    )

}
