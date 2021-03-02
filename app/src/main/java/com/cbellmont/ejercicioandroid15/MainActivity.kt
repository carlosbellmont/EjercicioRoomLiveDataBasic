package com.cbellmont.ejercicioandroid15

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.cbellmont.ejercicioandroid15.databinding.ActivityMainBinding
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    private lateinit var adapter : PersonajesAdapter
    private lateinit var binding : ActivityMainBinding
    private lateinit var model : MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        model =  ViewModelProvider(this).get(MainActivityViewModel::class.java)

        createRecyclerView()

        model.personajesList.observe(this) {
            adapter.updatePersonajes(it)
        }

        binding.radioGroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                binding.rbTodos.id -> loadAll()
                binding.rbBuenos.id -> loadBuenos()
                binding.rbMalos.id -> loadMalos()
            }
        }
        Toast.makeText(this, "Atención! Cuando se ejecuta por primera vez esta app no muestra los personajes.", Toast.LENGTH_LONG).show()
    }

    private fun createRecyclerView() {
        adapter = PersonajesAdapter(listOf())
        binding.recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
        binding.recyclerView.adapter = adapter
    }

    private fun loadAll(){
        model.getAll()
    }

    private fun loadBuenos(){
        model.getAllBuenos()

    }

    private fun loadMalos(){
        model.getAllMalos()
    }
}