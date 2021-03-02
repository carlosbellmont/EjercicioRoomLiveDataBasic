package com.cbellmont.ejercicioandroid15

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivityViewModel (application: Application) : AndroidViewModel(application){

    val personajesList: MutableLiveData<List<Personaje>> = MutableLiveData()

    init {
        getAll()
    }

    fun getAllBuenos(){
        viewModelScope.launch(Dispatchers.IO) {
            val list = Db.getDatabase(getApplication()).personajesDao().loadAllBuenos()
            withContext(Dispatchers.Main) {
                personajesList.value = list
            }
        }
    }

    fun getAllMalos(){
        viewModelScope.launch(Dispatchers.IO) {
            val list = Db.getDatabase(getApplication()).personajesDao().loadAllMalos()
            withContext(Dispatchers.Main) {
                personajesList.value = list
            }
        }    }

    fun getAll(){
        viewModelScope.launch(Dispatchers.IO) {
            val list = Db.getDatabase(getApplication()).personajesDao().getAll()
            withContext(Dispatchers.Main) {
                personajesList.value = list
            }
        }
    }


}