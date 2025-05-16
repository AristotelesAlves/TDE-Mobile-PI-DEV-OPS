package com.example.pi_mobile

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.enableEdgeToEdge

class CadastroActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.cadastro)

        val emailEditText = findViewById<EditText>(R.id.editTextTextEmailAddress)
        val senhaEditText1 = findViewById<EditText>(R.id.editTextNumberPassword1)
        val senhaEditText2 = findViewById<EditText>(R.id.editTextNumberPassword2)
        val cadastrarButton = findViewById<Button>(R.id.cadastrar)

        cadastrarButton.setOnClickListener {
            val email = emailEditText.text.toString().trim()
            val senha1 = senhaEditText1.text.toString()
            val senha2 = senhaEditText2.text.toString()

            when {
                email.isEmpty() || senha1.isEmpty() || senha2.isEmpty() -> {
                    showToast("Por favor, preencha todos os campos.")
                }

                !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                    showToast("Digite um email válido.")
                }

                senha1 != senha2 -> {
                    showToast("As senhas não coincidem.")
                }

                else -> {
                    showToast("Cadastro realizado com sucesso!")
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }
            }
        }
    }

    private fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}
