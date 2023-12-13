package com.example.mascotasapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mascotasapp.database.PropietariosAPP
import com.example.mascotasapp.databinding.ActivityDeleteBinding
import com.example.mascotasapp.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

// La clase DeleteActivity hereda de la clase ActivityWithMenus
class DeleteActivity : ActivityWithMenus() {
    // Override del método onCreate para inicializar la actividad
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflar el diseño de la actividad utilizando ViewBinding
        val binding= ActivityDeleteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configuración del listener para el botón de eliminación
        binding.btonEliminar.setOnClickListener {
            // Obtener el nombre del propietario desde el campo de texto
            var n_propietario = binding.escribePropietario.text.toString()

            // Iniciar un nuevo hilo de fondo con Coroutines
            CoroutineScope(Dispatchers.IO).launch {
                // Obtener la lista de mascotas asociadas a un propietario
                var listamascotas = PropietariosAPP.database.propirtariosDao().mascotasdeunpropietario(n_propietario)

                // Iterar sobre la lista de mascotas y eliminar cada una
                for(i in 0 until listamascotas.mascotas.size) {
                    PropietariosAPP.database.mascotasDAO().eliminarmascota(listamascotas.mascotas[i])
                }

                // Obtener el propietario y eliminarlo
                var prop = PropietariosAPP.database.propirtariosDao().obtenerpropietario(n_propietario)
                PropietariosAPP.database.propirtariosDao().eliminarpropietario(prop)
            }
        }
    }
}
