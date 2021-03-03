package com.cbellmont.ejercicioandroid15

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query


@Dao
interface MascotaDao {

    @Query("SELECT * FROM Mascota")
    fun getAll(): List<Mascota>


    @Insert
    fun insert(mascota: Mascota)

    @Delete
    fun delete(mascota: Mascota)
}