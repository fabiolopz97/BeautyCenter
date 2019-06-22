package com.beautycenter.android.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.beautycenter.android.R
import com.beautycenter.android.models.ServiceCategory
import kotlinx.android.synthetic.main.item_service.view.*

class ServiceCategoryAdapterRecyclerView (
    private val services: List<ServiceCategory>,
    private val layout: Int,
    private val listener: (ServiceCategory) -> Unit) :

    RecyclerView.Adapter<ServiceCategoryAdapterRecyclerView.MyViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, p1: Int): MyViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(layout, parent, false)
            return MyViewHolder(view)
        }

        override fun getItemCount(): Int = services.size

        override fun onBindViewHolder(viewHolder: MyViewHolder, position: Int) {
            val currentService = services[position]
            val txtTitle = viewHolder.itemView.textViewTitleService
            val imgIcon = viewHolder.itemView.imageViewService
            //val txtYear = viewHolder.itemView.txtMovieYear
            //val txtDescription = viewHolder.itemView.txtDescriptionItem


            txtTitle.text = currentService.name
            imgIcon.setImageResource(R.mipmap.logotype)
            //txtYear.text = currentMovie.year.toString()
            //txtDescription.text = currentMovie.description
            // val completeUrl = IMAGE_BASE_URL + currentMovie.image
            //Picasso.get().load(completeUrl).into(imgIcon)


            viewHolder.itemView.setOnClickListener { listener(currentService) }
        }

        class MyViewHolder(view: View): RecyclerView.ViewHolder(view)
}