package com.example.pi_mobile.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pi_mobile.R

data class ListingService (
    val title: String,
    val location: String,
    val date: String
)

class ServicoAdapter(private val listaServicos: List<ListingService>) :

    RecyclerView.Adapter<ServicoAdapter.ServicoViewHolder>() {

    class ServicoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title = itemView.findViewById<TextView>(R.id.txtTitulo)
        val location = itemView.findViewById<TextView>(R.id.textLocation)
        val date = itemView.findViewById<TextView>(R.id.txtDate)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServicoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.listing_fragment, parent, false)
        return ServicoViewHolder(view)
    }



    override fun onBindViewHolder(holder: ServicoViewHolder, position: Int) {
        val service = listaServicos[position]
        holder.title.text = service.title
        holder.location.text = service.location
        holder.date.text = service.date
    }

    override fun getItemCount(): Int = listaServicos.size
}
