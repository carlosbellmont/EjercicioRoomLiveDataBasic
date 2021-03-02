package com.cbellmont.ejercicioandroid15

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.cbellmont.ejercicioandroid15.databinding.ActivityMainBinding
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    private lateinit var adapter : PersonajesAdapter
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        createRecyclerView()

        binding.radioGroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                binding.rbTodos.id -> loadAll()
                binding.rbBuenos.id -> loadBuenos()
                binding.rbMalos.id -> loadMalos()
            }
        }
    }

    private fun createRecyclerView() {
        lifecycleScope.launch {
            withContext(Dispatchers.IO) {
                adapter = PersonajesAdapter(Db.getDatabase(this@MainActivity).personajesDao().getAll())
            }
            withContext(Dispatchers.Main) {
                binding.recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
                binding.recyclerView.adapter = adapter
            }
        }
    }

    private fun loadAll(){
        lifecycleScope.launch {
            withContext(Dispatchers.IO) {
                val listaPersonajes = Db.getDatabase(this@MainActivity).personajesDao().getAll()
                withContext(Dispatchers.Main) {
                    adapter.updatePersonajes(listaPersonajes)
                }
            }
        }
    }

    private fun loadBuenos(){
        lifecycleScope.launch {
            withContext(Dispatchers.IO) {
                val listaPersonajes = Db.getDatabase(this@MainActivity).personajesDao().loadAllBuenos()

                withContext(Dispatchers.Main) {
                    adapter.updatePersonajes(listaPersonajes)
                }
            }
        }
    }

    private fun loadMalos(){
        lifecycleScope.launch {
            withContext(Dispatchers.IO) {
                val listaPersonajes = Db.getDatabase(this@MainActivity).personajesDao().loadAllMalos()

                withContext(Dispatchers.Main) {
                    adapter.updatePersonajes(listaPersonajes)
                }
            }
        }
    }
}