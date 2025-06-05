package com.example.pi_mobile.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.pi_mobile.R
import com.example.pi_mobile.models.UserRequestDTO
import com.example.pi_mobile.models.UserResponseDTO
import com.example.pi_mobile.services.RetrofitInstance
import kotlinx.coroutines.launch

class CadastroActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.cadastro)

        val emailEditText = findViewById<EditText>(R.id.editTextTextEmailAddress)
        val senhaEditText1 = findViewById<EditText>(R.id.editTextNumberPassword1)
        val senhaEditText2 = findViewById<EditText>(R.id.editTextNumberPassword2)

        findViewById<Button>(R.id.cadastrar).setOnClickListener {
            val email = emailEditText.text.toString().trim()
            val senha1 = senhaEditText1.text.toString()
            val senha2 = senhaEditText2.text.toString()

            register(email, senha1, senha2)
        }
    }

    private fun register(email: String, password: String, confirmPassword: String) {
        if (!validateInputs(email, password, confirmPassword)) {
            return
        }

        val userRequest = UserRequestDTO(email, password)

        lifecycleScope.launch {
            try {
                val response = RetrofitInstance.authService.register(userRequest)
                if (!response.isSuccessful) {
                    Log.e(
                        this@CadastroActivity.toString(),
                        "Erro na resposta: ${response.code()} - ${response.message()}"
                    )
                    return@launch
                }

                val userResponse: UserResponseDTO = response.body()!!
                startActivity(Intent(this@CadastroActivity, LoginActivity::class.java))
            } catch (e: Exception) {
                Log.e(
                    this@CadastroActivity.toString(),
                    "Erro na requisição: ${e.localizedMessage}",
                    e
                )
            }
        }
    }

    private fun validateInputs(email: String, password: String, confirmPassword: String): Boolean {
        return when {
            email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() -> {
                showToast("Por favor, preencha todos os campos.")
                false
            }

            !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                showToast("Digite um email válido.")
                false
            }

            password != confirmPassword -> {
                showToast("As senhas não coincidem.")
                false
            }

            else -> true
        }
    }

    private fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}
