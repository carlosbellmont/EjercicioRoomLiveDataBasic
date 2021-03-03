package com.cbellmont.ejercicioandroid15

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Personaje(val nombre : String, val raza : String, val image : Int, val esBueno : Boolean) {
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0
}