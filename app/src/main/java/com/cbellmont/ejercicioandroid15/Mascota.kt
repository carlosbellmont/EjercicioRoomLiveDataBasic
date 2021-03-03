package com.cbellmont.ejercicioandroid15

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Mascota(var nombre : String, var tipo : String, var edad : Int) {
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0
}