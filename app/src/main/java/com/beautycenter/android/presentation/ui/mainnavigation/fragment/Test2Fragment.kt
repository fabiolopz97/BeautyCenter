package com.beautycenter.android.presentation.ui.mainnavigation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.beautycenter.android.R
import com.beautycenter.android.presentation.adapters.AppointmentAdapterRecyclerView
import com.beautycenter.android.data.MethodsUtils
import com.beautycenter.android.presentation.models.Appointment

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
        viewAdapter = AppointmentAdapterRecyclerView(appointment, R.layout.item_appointment){
            MethodsUtils.showMessage(v.context, "Appointment clicked: ${it.name}")
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
