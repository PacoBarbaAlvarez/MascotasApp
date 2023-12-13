package com.example.mascotasapp

import android.content.Intent
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity

// Clase base para actividades con menús
open class ActivityWithMenus : AppCompatActivity() {

    // Objeto companion para almacenar la actividad actual
    companion object {
        var actividadActual = 0
    }

    // Método que infla el menú en la barra de acción
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflar el menú desde el recurso de menú
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu, menu)

        // Deshabilitar la opción de menú correspondiente a la actividad actual
        for (i in 0 until menu.size()) {
            if (i == actividadActual) menu.getItem(i).isEnabled = false
            else menu.getItem(i).isEnabled = true
        }
        return true
    }

    // Método que maneja la selección de elementos del menú
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.anadir_mascota -> {
                // Configurar la actividad actual y lanzar MainActivity
                actividadActual = 0
                val intent = Intent(this, MainActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
                startActivity(intent)
                true
            }

            R.id.eliminar_mascota -> {
                // Configurar la actividad actual y lanzar DeleteActivity
                actividadActual = 1
                val intent = Intent(this, DeleteActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
                startActivity(intent)
                true
            }

            R.id.actualizar_mascota -> {
                // Configurar la actividad actual y lanzar UpdateActivity
                actividadActual = 2
                val intent = Intent(this, UpdateActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
                startActivity(intent)
                true
            }

            R.id.numeros_mascotas -> {
                // Configurar la actividad actual y lanzar MostrarActivity
                actividadActual = 3
                val intent = Intent(this, MostrarActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
                startActivity(intent)
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}
