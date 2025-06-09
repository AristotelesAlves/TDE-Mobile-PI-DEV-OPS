package com.example.pi_mobile.activity

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.pi_mobile.R
import com.example.pi_mobile.base.BaseActivity
import com.example.pi_mobile.fragment.SkillsDialogFragment

class NewService : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.new_service)

        setupDrawer(
            findViewById(R.id.drawer_layout),
            findViewById(R.id.menu),
            findViewById(R.id.navigation_view)
        )


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.drawer_layout)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val selectSkillsField = findViewById<TextView>(R.id.selectSkillsField)
        selectSkillsField.setOnClickListener {
            val dialog = SkillsDialogFragment()
            dialog.show(supportFragmentManager, "SkillsDialog")
        }
    }

    fun onSkillsSelected(skills: List<String>) {
        val selectSkillsField = findViewById<TextView>(R.id.selectSkillsField)
        selectSkillsField.text = skills.joinToString(", ")
    }
}
