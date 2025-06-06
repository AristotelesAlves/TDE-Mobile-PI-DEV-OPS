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
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.pi_mobile.R
import com.example.pi_mobile.services.RetrofitInstance
import kotlinx.coroutines.launch
import okhttp3.Credentials

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val email = findViewById<EditText>(R.id.editTextTextEmailAddress)
        val password = findViewById<EditText>(R.id.editTextNumberPassword2)
        findViewById<Button>(R.id.entrar).setOnClickListener {
            login(email.text.toString(), password.text.toString())
        }

        findViewById<Button>(R.id.btnEsqueceuSenha).setOnClickListener {
            val intent = Intent(this, ForgotPasswordActivity::class.java)
            startActivity(intent)
        }

        findViewById<Button>(R.id.cadastrar).setOnClickListener {
            val intent = Intent(this, CadastroActivity::class.java)
            startActivity(intent)
        }
    }

    private fun login(email: String, password: String) {
        when {
            email.isEmpty() || password.isEmpty() -> {
                showToast("Por favor, preencha todos os campos.")
                return
            }

            !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                showToast("Digite um email válido.")
                return
            }
        }

        lifecycleScope.launch {
            try {
                val response =
                    RetrofitInstance.authService.login(Credentials.basic(email, password))
                if (!response.isSuccessful) {
                    Log.e(
                        "LoginActivity",
                        "Erro na resposta: ${response.code()} - ${response.message()}"
                    )
                    return@launch
                }

                response.headers().get("authorization")?.let { token ->
                    RetrofitInstance.interceptor.token = token
                    Log.d("LoginActivity", "Token recebido: $token")
                } ?: run {
                    Log.e("LoginActivity", "Token não encontrado nos headers.")
                    showToast("Erro ao receber o token.")
                    return@launch
                }

                startActivity(Intent(this@LoginActivity, HomeActivity::class.java))
            } catch (e: Exception) {
                Log.e("LoginActivity", "Erro na requisição: ${e.localizedMessage}", e)
            }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
