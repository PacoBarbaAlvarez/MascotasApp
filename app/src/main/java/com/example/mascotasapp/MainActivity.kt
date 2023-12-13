package com.example.mascotasapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.CoroutinesRoom
import com.example.mascotasapp.database.Mascotas
import com.example.mascotasapp.database.Propietarios
import com.example.mascotasapp.database.PropietariosAPP
import com.example.mascotasapp.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ActivityWithMenus() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflar la vista usando ViewBinding
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configuración del listener para el botón btnGuardar
        binding.btnGuardar.setOnClickListener {
            // Obtener los datos de los campos de entrada
            var nom_prop = binding.nombrePropietario.text.toString()
            var dir_prop = binding.direccionPropietario.text.toString()
            var tlf_prop = binding.telefonoPropietario.text.toString()
            var nom_masc = binding.nombreMascota.text.toString()
            var raza_masc = binding.razaMascota.text.toString()
            var fec_masc = binding.fechaNacMascota.text.toString()
            var perro = binding.radioPerro.isChecked

            // Lanzar la operación en un hilo de fondo mediante Coroutines
            CoroutineScope(Dispatchers.IO).launch {
                // Insertar un nuevo propietario en la base de datos
                PropietariosAPP.database.propirtariosDao().anadirPropietario(
                    Propietarios(
                        nombre_propietario = nom_prop,
                        direccion_propietario = dir_prop,
                        tlf_propietario = tlf_prop,
                    )
                )
                // Insertar una nueva mascota en la base de datos
                PropietariosAPP.database.mascotasDAO().anadirmascota(
                    Mascotas(
                        nombre = nom_masc,
                        raza = raza_masc,
                        fecha_nac = fec_masc,
                        esPerro = perro,
                    )
                )
            }

            // Limpiar los campos de entrada después de la operación
            runOnUiThread {
                binding.nombreMascota.text.clear()
                binding.razaMascota.text.clear()
                binding.fechaNacMascota.text.clear()
                binding.radioPerro.isChecked = false
            }
        }

        // Configuración del listener para el botón btnOtraMascota
        binding.btnOtraMascota.setOnClickListener {
            // Obtener los datos de los campos de entrada
            var nom_masc = binding.nombreMascota.text.toString()
            var raza_masc = binding.razaMascota.text.toString()
            var fec_masc = binding.fechaNacMascota.text.toString()
            var perro = binding.radioPerro.isChecked

            // Lanzar la operación en un hilo de fondo mediante Coroutines
            CoroutineScope(Dispatchers.IO).launch {
                // Insertar una nueva mascota en la base de datos
                PropietariosAPP.database.mascotasDAO().anadirmascota(
                    Mascotas(
                        nombre = nom_masc,
                        raza = raza_masc,
                        fecha_nac = fec_masc,
                        esPerro = perro,
                    )
                )
            }

            // Limpiar los campos de entrada después de la operación
            runOnUiThread {
                binding.nombreMascota.text.clear()
                binding.razaMascota.text.clear()
                binding.fechaNacMascota.text.clear()
                binding.radioPerro.isChecked = false
            }
        }
    }
}
