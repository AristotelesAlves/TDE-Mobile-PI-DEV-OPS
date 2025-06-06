package com.example.pi_mobile.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pi_mobile.R
import com.example.pi_mobile.models.ListingResponseDTO

class ListingAdapter(private val listings: List<ListingResponseDTO>) :
    RecyclerView.Adapter<ListingViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListingViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.listing_fragment, parent, false)
        return ListingViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListingViewHolder, position: Int) {
        val service = listings[position]
        holder.title.text = service.title
        holder.location.text = service.location
        holder.date.text = service.creationDate
    }

    override fun getItemCount(): Int = listings.size
}
