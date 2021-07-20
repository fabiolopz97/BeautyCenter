package com.beautycenter.android.presentation.ui.servicedetail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.beautycenter.android.data.PathUtils
import com.beautycenter.android.presentation.models.Service
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_service.view.*

class ServiceAdapterRecyclerView(
    private val serviceResponses: List<Service>,
    private val layout: Int,
    private val listener: (Service) -> Unit) :

        RecyclerView.Adapter<ServiceAdapterRecyclerView.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(layout, parent, false)
        return MyViewHolder(
            view
        )
    }

    override fun getItemCount(): Int = serviceResponses.size

    override fun onBindViewHolder(viewHolder: MyViewHolder, position: Int) {
        val currentService = serviceResponses[position]
        val txtTitle = viewHolder.itemView.textViewTitleService
        val imgIcon = viewHolder.itemView.imageViewService
        //val txtYear = viewHolder.itemView.txtMovieYear
        //val txtDescription = viewHolder.itemView.txtDescriptionItem


        txtTitle.text = currentService.name
        Picasso.get()
            .load("${PathUtils.IMAGE_BASE_URL}img/${currentService.image}")
            .fit().centerCrop()
            .into(imgIcon)

        //txtYear.text = currentMovie.year.toString()
        //txtDescription.text = currentMovie.description
        // val completeUrl = IMAGE_BASE_URL + currentMovie.image
        //Picasso.get().load(completeUrl).into(imgIcon)


        viewHolder.itemView.setOnClickListener { listener(currentService) }
    }

    class MyViewHolder(view: View): RecyclerView.ViewHolder(view)

}