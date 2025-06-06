package com.example.pi_mobile.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pi_mobile.R

class ListingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val title: TextView = itemView.findViewById(R.id.txtTitulo)
    val location: TextView = itemView.findViewById(R.id.textLocation)
    val date: TextView = itemView.findViewById(R.id.txtDate)
}