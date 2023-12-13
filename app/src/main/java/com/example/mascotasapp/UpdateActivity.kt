package com.example.mascotasapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mascotasapp.database.Propietarios
import com.example.mascotasapp.database.PropietariosAPP
import com.example.mascotasapp.databinding.ActivityMainBinding
import com.example.mascotasapp.databinding.ActivityUpdateBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

// La clase UpdateActivity extiende de ActivityWithMenus
class UpdateActivity : ActivityWithMenus() {

    // Se declara la propiedad 'binding' de tipo ActivityUpdateBinding
    lateinit var binding: ActivityUpdateBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Se infla el diseño de la actividad utilizando el binding
        val binding = ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Se configura un listener para el botón de actualización
        binding.btnActualizar.setOnClickListener {
            // Se llama a la función actualizarpropietario con un objeto Propietarios creado a partir de los datos de la interfaz de usuario
            actualizarpropietario(Propietarios(
                nombre_propietario = binding.nPropietario.text.toString(),
                direccion_propietario = binding.nuevaDireccion.text.toString()
            ))
        }
    }

    // Función que actualiza un propietario en la base de datos utilizando corrutinas
    fun actualizarpropietario(elemento: Propietarios) {
        // Se lanza una corrutina en el hilo de fondo (IO)
        CoroutineScope(Dispatchers.IO).launch {
            // Se obtiene el propietario actual de la base de datos según el nombre
            val n = elemento.nombre_propietario
            var recoverypropietario = PropietariosAPP.database.propirtariosDao().obtenerpropietario(n)

            // Se actualizan los datos del propietario recuperado con los nuevos datos de la interfaz de usuario
            recoverypropietario.nombre_propietario = binding.nPropietario.text.toString()
            recoverypropietario.direccion_propietario = binding.nuevaDireccion.text.toString()

            // Se realiza la actualización del propietario en la base de datos
            PropietariosAPP.database.propirtariosDao().actualizarpropietario(elemento)
        }
    }
}
