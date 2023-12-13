package com.example.mascotasapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mascotasapp.database.PropietariosAPP
import com.example.mascotasapp.databinding.ActivityMainBinding
import com.example.mascotasapp.databinding.ActivityMostrarBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MostrarActivity : ActivityWithMenus() {
    // Método onCreate que se llama cuando se crea la actividad
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        // Llama al método onCreate de la superclase
        super.onCreate(savedInstanceState)

        // Infla el diseño de la actividad usando el enlace (binding) generado por ViewBinding
        val binding = ActivityMostrarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configura un OnClickListener para el botón btnMostrar
        binding.btnMostrar.setOnClickListener {
            // Obtiene el propietario introducido desde el campo de texto nPropietario
            var propietario_introducido = binding.nPropietario.text.toString()

            // Utiliza Coroutines para realizar operaciones en un hilo de fondo (IO)
            CoroutineScope(Dispatchers.IO).launch {
                // Obtiene la lista de perros y gatos para el propietario introducido
                var listaPerros = PropietariosAPP.database.propirtariosDao().mascotasdeunpropietario(propietario_introducido)
                var listaGatos = PropietariosAPP.database.propirtariosDao().mascotasdeunpropietario(propietario_introducido)

                // Actualiza la interfaz de usuario en el hilo principal
                runOnUiThread {
                    // Muestra el número de perros y gatos en los TextView correspondientes
                    binding.numPerros.text = "Número de perros: ${listaPerros}"  // Suponiendo que listaPerros es una lista
                    binding.numGatos.text = "Número de gatos: ${listaGatos}"     // Suponiendo que listaGatos es una lista
                }
            }
        }
    }
}