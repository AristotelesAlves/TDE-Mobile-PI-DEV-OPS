package com.example.pi_mobile

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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

        // Chamada Retrofit
        lifecycleScope.launch {
            try {
                val response = RetrofitInstance.ServiceListing.listing()
                if (response.isSuccessful && response.body() != null) {
                    val listaResponse = response.body()!!
                    print(listaResponse)
                    val listaConvertida = listaResponse.content.map {
                        ListingService(
                            title = it.title,
                            location = it.location,
                            date = it.creationDate.toString()
                        )
                    }

                    servicoAdapter = ServicoAdapter(listaConvertida)
                    recyclerView.adapter = servicoAdapter
                } else {
                    println("error")
                }
            } catch (e: Exception) {
                e.printStackTrace()
                println("Erro na requisição: ${e.localizedMessage}")
            }
        }
    }
}
