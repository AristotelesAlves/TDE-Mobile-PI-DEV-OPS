package com.example.pi_mobile.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.pi_mobile.R

class NewPasswordActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_new_password)

        val senhaEditText = findViewById<EditText>(R.id.editTextNumberPassword1)
        val confirmarSenhaEditText = findViewById<EditText>(R.id.editTextNumberPassword2)
        val salvarButton = findViewById<Button>(R.id.cadastrar)

        salvarButton.setOnClickListener {
            val senha = senhaEditText.text.toString().trim()
            val confirmarSenha = confirmarSenhaEditText.text.toString().trim()

            when {
                senha.isEmpty() || confirmarSenha.isEmpty() -> {
                    showToast("Preencha todos os campos.")
                }

                senha != confirmarSenha -> {
                    showToast("As senhas não coincidem.")
                }

                else -> {
                    showToast("Senha redefinida com sucesso!")
                    val intent = Intent(this, HomeActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        }
    }

    private fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}
