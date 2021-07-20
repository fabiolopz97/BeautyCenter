package com.beautycenter.android.presentation.ui.mainnavigation.fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.beautycenter.android.R
import com.beautycenter.android.data.PathUtils
import com.beautycenter.android.presentation.models.ServiceCategory
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_service.view.*

class ServiceCategoryAdapterRecyclerView (
    private val services: List<ServiceCategory>,
    private val layout: Int,
    private val listener: (ServiceCategory) -> Unit) :

    RecyclerView.Adapter<ServiceCategoryAdapterRecyclerView.MyViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, p1: Int): MyViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(layout, parent, false)
            return MyViewHolder(
                view
            )
        }

        override fun getItemCount(): Int = services.size

        override fun onBindViewHolder(viewHolder: MyViewHolder, position: Int) {
            val currentService = services[position]
            val txtTitle = viewHolder.itemView.textViewTitleService
            val imgIcon = viewHolder.itemView.imageViewService

            txtTitle.text = currentService.name
            Picasso.get()
                .load("${PathUtils.IMAGE_BASE_URL}img/${currentService.image}")
                .fit().centerCrop()
                .into(imgIcon)

            viewHolder.itemView.setOnClickListener { listener(currentService) }
        }

        class MyViewHolder(view: View): RecyclerView.ViewHolder(view)
}