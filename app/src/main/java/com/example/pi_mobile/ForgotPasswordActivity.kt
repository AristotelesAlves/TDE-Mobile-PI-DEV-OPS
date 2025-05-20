package com.example.pi_mobile

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.enableEdgeToEdge

class ForgotPasswordActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_forgot_password)

        val emailEditText = findViewById<EditText>(R.id.editTextTextEmailAddress)
        val enviarButton = findViewById<Button>(R.id.entrar)
        val voltarButton = findViewById<Button>(R.id.cadastrar2)

        enviarButton.setOnClickListener {
            val email = emailEditText.text.toString().trim()

            when {
                email.isEmpty() -> {
                    showToast("Por favor, insira seu e-mail.")
                }

                !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                    showToast("Digite um e-mail válido.")
                }

                else -> {
                    showToast("Código enviado para seu e-mail.")
                    val intent = Intent(this, CodeForgotActivity::class.java)
                    intent.putExtra("email", email)
                    startActivity(intent)
                }
            }
        }

        voltarButton.setOnClickListener {
            finish()
        }
    }

    private fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}
