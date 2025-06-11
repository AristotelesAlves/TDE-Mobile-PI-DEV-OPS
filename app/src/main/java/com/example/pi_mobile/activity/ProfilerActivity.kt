package com.example.pi_mobile.activity

import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.lifecycleScope
import com.example.pi_mobile.R
import com.example.pi_mobile.base.BaseActivity
import com.example.pi_mobile.models.UserProfileResponseDTO
import com.example.pi_mobile.services.RetrofitInstance // -> vou mostrar embaixo como fazer esse RetrofitInstance
import kotlinx.coroutines.launch

class ProfilerActivity : BaseActivity() {

    private lateinit var txtName: TextView
    private lateinit var txtPhone: TextView
    private lateinit var txtAddress: TextView
    private lateinit var txtDescription: TextView
    private lateinit var txtSkills: TextView
    private lateinit var txtTitle: TextView
    private lateinit var textDocument: TextView
    private lateinit var textNomeTop: TextView
    private lateinit var textTagTop: TextView
    private lateinit var accountId: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.perfil)

        setupDrawer(
            findViewById(R.id.drawer_layout),
            findViewById(R.id.menu),
            findViewById(R.id.navigation_view)
        )



        txtName = findViewById(R.id.name)
        txtPhone = findViewById(R.id.contato)
        txtAddress = findViewById(R.id.sobre)
        textDocument = findViewById(R.id.cpf)
        txtDescription = findViewById(R.id.localizacao)
        txtSkills = findViewById(R.id.habilidades)
        txtTitle = findViewById(R.id.titulo)
        textNomeTop = findViewById(R.id.nameTop)
        textTagTop = findViewById(R.id.tagTop)

        val token = RetrofitInstance.interceptor.token
        val parts = token.split(".")

        if (parts.size == 3) {
            val payload = String(android.util.Base64.decode(parts[1], android.util.Base64.URL_SAFE))
            val json = org.json.JSONObject(payload)
            accountId = json.optString("accountId")
        } else {
            Log.e("LoginActivity", "Formato do token JWT inválido.")
            showToast("Token inválido.")
            return
        }

        carregarPerfil()

        val servicosContratadosLayout = findViewById<LinearLayout>(R.id.servicos_contratados)

        val inflater = layoutInflater

        carregarServicosPublicados()

        repeat(3) {
            val card = inflater.inflate(R.layout.listing_fragment, servicosContratadosLayout, false)
            card.findViewById<TextView>(R.id.txtTitulo).text = "Serviço Contratado ${it + 1}"
            card.findViewById<TextView>(R.id.textLocation).text = "Local ${it + 1}"
            card.findViewById<TextView>(R.id.txtDate).text = "11/06/2025"
            servicosContratadosLayout.addView(card)
        }
    }


    private fun carregarPerfil() {

        Log.d("Perfil", "ID:$accountId")

        val id = accountId.toLong()

        Log.d("Perfil", "ID_LONG:$id")


        lifecycleScope.launch {
            try {
                val service = RetrofitInstance.userProfileService
                val response = service.findById(id)
                Log.e("Perfil", "id perfil: $accountId")
                if (response.isSuccessful) {
                    response.body()?.let { preencherPerfil(it) }
                } else {
                    Toast.makeText(this@ProfilerActivity, "Erro: ${response.code()}", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                Log.e("Perfil", "Erro carregando perfil", e)
                Toast.makeText(this@ProfilerActivity, "Erro ao carregar perfil", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun preencherPerfil(profile: UserProfileResponseDTO) {
        textTagTop.text = profile.title
        textNomeTop.text = profile.name
        txtName.text = profile.name
        txtPhone.text = profile.phone
        txtAddress.text = profile.address
        txtDescription.text = profile.description
        textDocument.text = profile.document
        txtTitle.text = profile.title
        txtSkills.text = profile.skills.joinToString(", ")
    }

    private fun carregarServicosPublicados() {
        val servicosPrestadosLayout = findViewById<LinearLayout>(R.id.servicos_prestados)
        val inflater = layoutInflater

        lifecycleScope.launch {
            try {
                val service = RetrofitInstance.listingService
                val response = service.findAllByAccountIdProfile(accountId = accountId.toInt())

                if (response.isSuccessful) {
                    val listings = response.body()?.content ?: emptyList()

                    servicosPrestadosLayout.removeAllViews()

                    listings.forEach { listing ->
                        val card = inflater.inflate(R.layout.listing_fragment, servicosPrestadosLayout, false)
                        card.findViewById<TextView>(R.id.txtTitulo).text = listing.title
                        card.findViewById<TextView>(R.id.textLocation).text = listing.location
                        card.findViewById<TextView>(R.id.txtDate).text = listing.creationDate
                        servicosPrestadosLayout.addView(card)
                    }
                } else {
                    showToast("Erro carregando serviços: ${response.code()}")
                }
            } catch (e: Exception) {
                Log.e("Serviços", "Erro carregando serviços publicados", e)
                showToast("Erro carregando serviços.")
            }
        }
    }


    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
