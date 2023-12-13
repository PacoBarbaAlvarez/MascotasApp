package com.example.mascotasapp.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update

@Dao
interface MascotasDAO {

    @Insert
    fun anadirmascota(mascota:Mascotas)

    @Update
    fun actualizarmascota(mascota:Mascotas)

    @Delete
    fun eliminarmascota(mascota:Mascotas)
}