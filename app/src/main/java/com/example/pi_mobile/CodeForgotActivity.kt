package com.example.pi_mobile

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class CodeForgotActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_code_forgot)

        val codigoEditText = findViewById<EditText>(R.id.editTextTextEmailAddress)
        val enviarButton = findViewById<Button>(R.id.entrar)
        val voltarButton = findViewById<Button>(R.id.cadastrar2)

        enviarButton.setOnClickListener {
            val codigo = codigoEditText.text.toString().trim()

            when {
                codigo.isEmpty() -> {
                    showToast("Por favor, insira o código de verificação.")
                }

                codigo != "12345" -> {
                    showToast("Código inválido.")
                }

                else -> {
                    showToast("Código verificado com sucesso!")
                    val intent = Intent(this, NewPasswordActivity::class.java)
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
