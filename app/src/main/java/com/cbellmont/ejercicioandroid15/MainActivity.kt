package com.cbellmont.ejercicioandroid15

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    private lateinit var adapter : PersonajesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        createRecyclerView()

        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                rbTodos.id -> loadAll()
                rbBuenos.id -> loadBuenos()
                rbMalos.id -> loadMalos()
            }
        }
    }

    private fun createRecyclerView() {
        CoroutineScope(Dispatchers.IO).launch {
            withContext(Dispatchers.IO) {
                adapter = PersonajesAdapter(App.getDatabase(this@MainActivity).PersonajesDao().getAll())
            }
            withContext(Dispatchers.Main) {
                recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
                recyclerView.adapter = adapter
            }
        }
    }

    private fun loadAll(){
        CoroutineScope(Dispatchers.IO).launch {
            withContext(Dispatchers.IO) {
                val listaPersonajes = App.getDatabase(this@MainActivity).PersonajesDao().getAll()
                withContext(Dispatchers.Main) {
                    adapter.updatePersonajes(listaPersonajes)
                }
            }
        }
    }

    private fun loadBuenos(){
        CoroutineScope(Dispatchers.IO).launch {
            withContext(Dispatchers.IO) {
                val listaPersonajes = App.getDatabase(this@MainActivity).PersonajesDao().loadAllBuenos()

                withContext(Dispatchers.Main) {
                    adapter.updatePersonajes(listaPersonajes)
                }
            }
        }
    }

    private fun loadMalos(){
        CoroutineScope(Dispatchers.IO).launch {
            withContext(Dispatchers.IO) {
                val listaPersonajes = App.getDatabase(this@MainActivity).PersonajesDao().loadAllMalos()

                withContext(Dispatchers.Main) {
                    adapter.updatePersonajes(listaPersonajes)
                }
            }
        }
    }
}