package com.example.pi_mobile.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.recyclerview.widget.RecyclerView
import com.example.pi_mobile.R

class SkillsAdapter(private val originalList: List<String>) : RecyclerView.Adapter<SkillsAdapter.ViewHolder>() {

    private val filteredList = originalList.toMutableList()
    private val selectedItems = mutableSetOf<String>()

    fun filter(query: String) {
        filteredList.clear()
        filteredList.addAll(originalList.filter { it.contains(query, ignoreCase = true) })
        notifyDataSetChanged()
    }

    fun getSelectedSkills(): List<String> = selectedItems.toList()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val checkBox: CheckBox = itemView.findViewById(R.id.checkbox_skill)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_skill, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = filteredList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val skill = filteredList[position]
        holder.checkBox.text = skill
        holder.checkBox.isChecked = selectedItems.contains(skill)

        holder.checkBox.setOnCheckedChangeListener(null)
        holder.checkBox.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) selectedItems.add(skill) else selectedItems.remove(skill)
        }
    }
}
