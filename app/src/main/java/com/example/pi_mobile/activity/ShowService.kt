package com.example.pi_mobile.activity

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import com.example.pi_mobile.R
import com.example.pi_mobile.base.BaseActivity
import com.example.pi_mobile.services.ListingService
import com.example.pi_mobile.services.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ShowService : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_service)

        setupDrawer(
            findViewById(R.id.drawer_layout),
            findViewById(R.id.menu),
            findViewById(R.id.navigation_view)
        )


        val id = intent.getLongExtra("LISTING_ID", -1)

        if (id != -1L) {
            getListingById(id.toLong())
        } else {
            Log.e("ShowService", "ID inválido recebido")
        }
    }

    private fun getListingById(id: Long) {
        lifecycleScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    RetrofitInstance.listingService.findById(id)
                }

                if (response.isSuccessful) {
                    val listing = response.body()
                    listing?.let { fillViews(it) }
                } else {
                    Log.e("ShowService", "Erro na API: ${response.code()}")
                }
            } catch (e: Exception) {
                Log.e("ShowService", "Erro ao buscar serviço", e)
            }
        }
    }

    private fun fillViews(listing: ListingService.ListingResponsev2DTO) {
        findViewById<TextView>(R.id.nomeServicoTextView).text = listing.title
        findViewById<TextView>(R.id.tagsServicoTextView).text = listing.skills.joinToString(", ")
        findViewById<TextView>(R.id.nomeUsuarioTextView).text = listing.userProfile.name
        findViewById<TextView>(R.id.tagUsuarioTextView).text = listing.userProfile.title
        findViewById<TextView>(R.id.localizacaoTextView).text = listing.location
        findViewById<TextView>(R.id.valorServicoTextView).text = "R$ ${listing.price}"
        findViewById<TextView>(R.id.dataPostagemTextView).text = listing.creationDate
    }
}
