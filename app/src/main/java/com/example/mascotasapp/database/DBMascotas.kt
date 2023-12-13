package com.example.mascotasapp.database

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(
    entities = [Propietarios::class, Mascotas::class],
    version = 1
)

abstract class DBMascotas: RoomDatabase() {

    abstract fun propirtariosDao(): PropietariosDAO

    abstract fun mascotasDAO(): MascotasDAO

}