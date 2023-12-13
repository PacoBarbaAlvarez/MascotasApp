package com.example.mascotasapp.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update


@Dao
interface PropietariosDAO {

    @Insert
    fun anadirPropietario(propietario:Propietarios)

    @Transaction
    @Query("SELECT * FROM propietarios WHERE nombre_propietario like :propietario")
    fun mascotasdeunpropietario(propietario: String): PropietariosConMascotas

    @Query("SELECT * FROM propietarios WHERE nombre_propietario like :propietario")
    fun obtenerpropietario(propietario: String): Propietarios

    @Update
    fun actualizarpropietario(propietario: Propietarios)

    @Delete
    fun eliminarpropietario(propietario: Propietarios)


}