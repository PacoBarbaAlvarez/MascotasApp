package com.example.mascotasapp.database

import android.app.Application
import androidx.room.Room

class PropietariosAPP:Application() {

    companion object{

        lateinit var  database: DBMascotas
    }

    override fun onCreate() {
        super.onCreate()
        PropietariosAPP.database = Room.databaseBuilder(this, DBMascotas::class.java, "DBMascotas").build()
    }
}