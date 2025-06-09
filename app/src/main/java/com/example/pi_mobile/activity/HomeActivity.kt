package com.example.pi_mobile.activity

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pi_mobile.R
import com.example.pi_mobile.adapter.ListingAdapter
import com.example.pi_mobile.base.BaseActivity
import com.example.pi_mobile.services.RetrofitInstance
import com.google.android.material.navigation.NavigationView
import kotlinx.coroutines.launch

class HomeActivity : BaseActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var listingAdapter: ListingAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.servicos)

        setupDrawer(
            findViewById(R.id.drawer_layout),
            findViewById(R.id.menu),
            findViewById(R.id.navigation_view)
        )

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
