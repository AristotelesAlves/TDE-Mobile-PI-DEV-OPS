package com.example.pi_mobile.fragment

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pi_mobile.R
import com.example.pi_mobile.activity.SecundRegisterProfiler
import com.example.pi_mobile.adapter.SkillsAdapter

class SkillsDialogFragment : DialogFragment() {

    private lateinit var adapter: SkillsAdapter

    private val allSkills = listOf("Kotlin", "Java", "React", "Node.js", "SQL", "Android", "Figma", "Python")

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireContext())
        val view = LayoutInflater.from(context).inflate(R.layout.dialog_skills_selector, null)
        val searchInput = view.findViewById<EditText>(R.id.search_input)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_skills)

        adapter = SkillsAdapter(allSkills)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter

        searchInput.doAfterTextChanged {
            val query = it.toString().lowercase()
            adapter.filter(query)
        }

        builder.setView(view)
            .setPositiveButton("OK") { _, _ ->
                val selectedSkills = adapter.getSelectedSkills()
                (activity as? SecundRegisterProfiler)?.onSkillsSelected(selectedSkills)
            }
            .setNegativeButton("Cancelar", null)

        return builder.create()
    }
}
