package com.example.mascotasapp.database
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "mascotas")
data class Mascotas(
    @PrimaryKey
    var id:Int=0,
    var nombre:String="",
    var esPerro:Boolean=false,
    var raza:String="",
    var fecha_nac:String="",
    var duenio:String=""

)
