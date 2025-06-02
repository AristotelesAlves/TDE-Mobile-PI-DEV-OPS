package com.example.pi_mobile.activity

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pi_mobile.R
import com.example.pi_mobile.adapter.ListingService
import com.example.pi_mobile.adapter.ServicoAdapter
import com.example.pi_mobile.services.RetrofitInstance
import kotlinx.coroutines.launch

class HomeActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var servicoAdapter: ServicoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.servicos)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        lifecycleScope.launch {
            Log.d("HomeActivity", "Iniciando requisição")
            try {
                val response = RetrofitInstance.ServiceListing.listing()
                Log.d("HomeActivity", "Requisição finalizada: ${response.code()} ${response.message()}")

                if (response.isSuccessful && response.body() != null) {
                    val listaResponse = response.body()!!
                    val listaConvertida = listaResponse.content.map {
                        ListingService(
                            title = it.title,
                            location = it.location,
                            date = it.creationDate.toString()
                        )
                    }

                    Log.d("HomeActivity", "Itens retornados: ${listaConvertida.size}")
                    if (listaConvertida.isEmpty()) {
                        Log.w("HomeActivity", "A lista está vazia!")
                    } else {
                        for (servico in listaConvertida) {
                            Log.d("HomeActivity", "Título: ${servico.title}, Local: ${servico.location}, Data: ${servico.date}")
                        }
                    }

                    servicoAdapter = ServicoAdapter(listaConvertida)
                    recyclerView.adapter = servicoAdapter
                } else {
                    Log.e("HomeActivity", "Erro na resposta: ${response.code()} - ${response.message()}")
                    if (response.errorBody() != null) {
                        Log.e("HomeActivity", "Body do erro: ${response.errorBody()!!.string()}")
                    }
                }
            } catch (e: Exception) {
                Log.e("HomeActivity", "Erro na requisição: ${e.localizedMessage}", e)
            }
        }
    }
}