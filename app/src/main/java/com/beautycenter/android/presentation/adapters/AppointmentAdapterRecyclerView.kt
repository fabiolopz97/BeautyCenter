package com.beautycenter.android.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.beautycenter.android.R
import com.beautycenter.android.presentation.models.Appointment
import kotlinx.android.synthetic.main.item_appointment.view.*

class AppointmentAdapterRecyclerView (
    private val appointment: List<Appointment>,
    private val layout: Int,
    private val listener: (Appointment) -> Unit) :

    RecyclerView.Adapter<AppointmentAdapterRecyclerView.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(layout, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int = appointment.size

    override fun onBindViewHolder(viewHolder: MyViewHolder, position: Int) {
        val currentAppointment = appointment[position]
        val txtTitle = viewHolder.itemView.textViewTitleAppointment
        val txtDescription = viewHolder.itemView.textViewDescriptionAppointment
        val imgIcon = viewHolder.itemView.imageViewAppointment

        txtTitle.text = currentAppointment.name
        txtDescription.text = currentAppointment.description
        imgIcon.setImageResource(R.mipmap.logotype)

        viewHolder.itemView.setOnClickListener { listener(currentAppointment) }
    }

    class MyViewHolder(view: View): RecyclerView.ViewHolder(view)

}