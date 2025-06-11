package com.example.pi_mobile.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.pi_mobile.R
import com.example.pi_mobile.fragment.SkillsDialogFragment
import com.example.pi_mobile.models.UserProfileRequestDTO
import com.example.pi_mobile.models.UserProfileResponseDTO
import com.example.pi_mobile.services.RetrofitInstance
import kotlinx.coroutines.launch

class SecundRegisterProfiler : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_secund_register_profiler)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val selectSkillsField = findViewById<TextView>(R.id.selectSkillsField)
        selectSkillsField.setOnClickListener {
            val dialog = SkillsDialogFragment()
            dialog.show(supportFragmentManager, "SkillsDialog")
        }

        val handleSubmit = findViewById<Button>(R.id.buttonSalvarInformacoes)

        handleSubmit.setOnClickListener {
            val name = findViewById<EditText>(R.id.name).text.toString()
            val phone = findViewById<EditText>(R.id.phone).text.toString()
            val address = findViewById<EditText>(R.id.address).text.toString()
            val postalCode = findViewById<EditText>(R.id.postalCode).text.toString()
            val cpf = findViewById<EditText>(R.id.cpf).text.toString()
            val description = findViewById<EditText>(R.id.description).text.toString()
            val title = findViewById<EditText>(R.id.title).text.toString()
            val skillsText = findViewById<TextView>(R.id.selectSkillsField).text.toString()
            val skills = skillsText.split(",").map { it.trim() }.toSet()

            val data = UserProfileRequestDTO(
                name = name,
                phone = phone,
                address = address,
                postalCode = postalCode,
                skills = skills,
                document = cpf,
                description = description,
                title = title
            )

            handleSubmit(data)

        }
    }

    private fun handleSubmit(data: UserProfileRequestDTO){
        lifecycleScope.launch {
            try {
                val response = RetrofitInstance.userProfileService.create(data)
                if (!response.isSuccessful) {
                    Log.e(
                        "handleSubmit",
                        "Erro na resposta: ${response.code()} - ${response.message()}"
                    )
                    return@launch
                }

                val userResponse: UserProfileResponseDTO = response.body()!!
                Log.d("handleSubmit","OLHA ESSA MERDA: $response")
                startActivity(Intent(this@SecundRegisterProfiler, HomeActivity::class.java))
            } catch (e: Exception) {
                Log.e("handleSubmit",
                    "Erro na requisição: ${e.localizedMessage}",
                    e
                )
            }
        }
    }

    fun onSkillsSelected(skills: List<String>) {
        val selectSkillsField = findViewById<TextView>(R.id.selectSkillsField)
        selectSkillsField.text = skills.joinToString(", ")
    }
}
