package com.example.horarioasistencia

import android.content.ContentValues
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import database.MiBaseDeDatosHelper
import Empleado
import java.io.ByteArrayOutputStream


class Agregarempleado : AppCompatActivity() {

    private lateinit var dbHelper: MiBaseDeDatosHelper
    private val REQUEST_IMAGE_CAPTURE = 1

    // Declarar una variable para el empleado
    private var empleado: Empleado? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregarempleado)
        Log.d("Agregarempleado", "onCreate ejecutado") // Agregar este mensaje de registro

        // Inicializa el dbHelper
        dbHelper = MiBaseDeDatosHelper(this)

        val nombreEditText = findViewById<EditText>(R.id.txtnombre)
        val semanaEditText = findViewById<EditText>(R.id.txtsemana)
        val miercolesEditText = findViewById<EditText>(R.id.txtmiercoles)
        val juevesEditText = findViewById<EditText>(R.id.txtjueves)
        val viernesEditText = findViewById<EditText>(R.id.txtviernes)
        val sabadoEditText = findViewById<EditText>(R.id.txtsabado)
        val domingoEditText = findViewById<EditText>(R.id.txtdomingo)
        val lunesEditText = findViewById<EditText>(R.id.txtlunes)
        val horaextraEditText = findViewById<EditText>(R.id.txthoraextra)

        val guardarButton = findViewById<Button>(R.id.btnguardar)
        guardarButton.setOnClickListener {
            val nombre = nombreEditText.text.toString()
            val semana = semanaEditText.text.toString().toInt()
            val miercoles = miercolesEditText.text.toString().toDouble()
            val jueves = juevesEditText.text.toString().toDouble()
            val viernes = viernesEditText.text.toString().toDouble()
            val sabado = sabadoEditText.text.toString().toDouble()
            val domingo = domingoEditText.text.toString().toDouble()
            val lunes = lunesEditText.text.toString().toDouble()
            val horaextra = horaextraEditText.text.toString().toDouble()

            val db = dbHelper.writableDatabase
            val contentValues = ContentValues()
            contentValues.put(MiBaseDeDatosHelper.COLUMN_NOMBRE, nombre)
            contentValues.put(MiBaseDeDatosHelper.COLUMN_SEMANA, semana)
            contentValues.put(MiBaseDeDatosHelper.COLUMN_MIERCOLES, miercoles)
            contentValues.put(MiBaseDeDatosHelper.COLUMN_JUEVES, jueves)
            contentValues.put(MiBaseDeDatosHelper.COLUMN_VIERNES, viernes)
            contentValues.put(MiBaseDeDatosHelper.COLUMN_SABADO, sabado)
            contentValues.put(MiBaseDeDatosHelper.COLUMN_DOMINGO, domingo)
            contentValues.put(MiBaseDeDatosHelper.COLUMN_LUNES, lunes)
            contentValues.put(MiBaseDeDatosHelper.COLUMN_HORA_EXTRA, horaextra)

            // Inserta el registro en la tabla de empleados
            val resultado = db.insert(MiBaseDeDatosHelper.TABLE_EMPLEADO, null, contentValues)

            // Cierra la base de datos después de usarla
            db.close()
        }

        val tomarFotoButton = findViewById<Button>(R.id.btnrostro)
        tomarFotoButton.setOnClickListener {
            // Lanzar la cámara para tomar una foto
            val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            if (takePictureIntent.resolveActivity(packageManager) != null) {
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
            }
        }

        val btnVolver = findViewById<Button>(R.id.btnvolvera)
        btnVolver.setOnClickListener {
            val intent = Intent(this, Menuopciones::class.java)
            startActivity(intent)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        Log.d("Agregarempleado", "onActivityResult ejecutado")

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            val imageBitmap = data?.extras?.get("data") as Bitmap?
            if (imageBitmap != null) {
                val stream = ByteArrayOutputStream()
                imageBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
                val byteArray = stream.toByteArray()
                empleado?.rostro = byteArray
            }
        }
    }
}