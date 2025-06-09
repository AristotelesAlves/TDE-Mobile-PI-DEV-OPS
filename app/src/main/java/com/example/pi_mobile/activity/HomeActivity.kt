package com.example.pi_mobile.activity

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pi_mobile.R
import com.example.pi_mobile.adapter.ListingAdapter
import com.example.pi_mobile.services.RetrofitInstance
import kotlinx.coroutines.launch

class HomeActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var listingAdapter: ListingAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.servicos)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        fetchServices()
    }

    private fun fetchServices() {
        lifecycleScope.launch {
            try {
                val response = RetrofitInstance.listingService.findAll()
                if (!response.isSuccessful) {
                    Log.e("HomeActivity", "Erro na resposta: ${response.code()} - ${response.message()}")
                    return@launch
                }

                val body = response.body()!!
                listingAdapter = ListingAdapter(body.content)
                recyclerView.adapter = listingAdapter
            } catch (e: Exception) {
                Log.e("HomeActivity", "Erro na requisição: ${e.localizedMessage}", e)
            }
        }
    }
}