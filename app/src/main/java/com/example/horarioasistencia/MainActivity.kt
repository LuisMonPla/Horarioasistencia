package com.example.horarioasistencia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val passwordEditText = findViewById<EditText>(R.id.txtpassword)
        val submitButton = findViewById<FloatingActionButton>(R.id.btnbuscar)

        // Reemplaza "1234" con tu clave correcta
        val claveCorrecta = "1234"

        submitButton.setOnClickListener {
            val inputClave = passwordEditText.text.toString()

            if (inputClave == claveCorrecta) {
                // La clave es correcta, abre la nueva ventana
                val intent = Intent(this, Menuopciones::class.java)
                startActivity(intent)
            } else {
                // La clave es incorrecta, muestra un mensaje de error
                val mensajeError = "Contraseña incorrecta"
                val duracionToast = Toast.LENGTH_SHORT // Puedes usar Toast.LENGTH_LONG si deseas que dure más tiempo

                val toast = Toast.makeText(this, mensajeError, duracionToast)
                toast.show()
            }
        }
    }
}